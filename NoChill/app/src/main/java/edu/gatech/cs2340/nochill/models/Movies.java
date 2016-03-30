package edu.gatech.cs2340.nochill.models;

import android.graphics.Movie;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Baijun on 2/27/2016.
 */
public class Movies {

    private static RequestQueue queue = Requests.getQueue();

    private static HashMap<Integer,MovieItem> movieMap = new HashMap<Integer,MovieItem>();

    /**
     * Returns movie associated with id
     * @param id id of movie as given by RT
     * @return MovieItem object if in storage or else null
     */
    public static void getMovie(final Integer id, Response.Listener<String> rl, Response.ErrorListener el){
        StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/movies/getMovie", rl, el){
            @Override
            protected Map<String,String> getParams(){

                Map<String,String> params = new HashMap<String, String>();
                params.put("id",id.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    /**
     * Adds movie to storage
     * @param m MovieItem to be added. If it exists, update it.
     * @return null if movie did not exist or old MovieItem
     */
    public static void addMovie(final MovieItem m, Response.Listener<String> rl, Response.ErrorListener el){
        StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/movies/addMovie", rl, el){
            @Override
            protected Map<String,String> getParams(){

                String name = m.getName();
                int id = m.getID();
                int year = m.getYear();
                String rating_mpaa = m.getRating_mpaa();
                String description = m.getDescription();
                double averageRaring = m.getAverageRating();
                int numRatings = m.getNumRatings();
                List<String> actors = m.getActors();

                JSONObject j = new JSONObject();
                try {
                    j.put("name", name);
                    j.put("id", id);
                    j.put("year", year);
                    j.put("rating_mpaa", rating_mpaa);
                    j.put("description", description);
                    j.put("averageRating", averageRaring);
                    j.put("numRatings", numRatings);
                    j.put("actors", "[a1, a2, a3]");
                } catch (Exception e){
                    Log.i("addUSer Error: ", "get param error");
                }

                JSONObject j2 = new JSONObject();
                try {
                    j2.put("major", CurrentUser.getProfile().getMajor());
                    j2.put("rating", 1);
                    j2.put("count", 1);
                } catch (Exception e){
                    Log.i("addUSer Error: ", "get param error");
                }
                Log.i("PARAMETERS: ", j.toString());
                Map<String,String> params = new HashMap<String, String>();
                params.put("movieString",j.toString());
                params.put("majorRatingString",j2.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    /**
     * Removes movie from storage
     * @param id id of movie to be removed as given by RT
     * @return MovieItem of removed movie or null if it was not in storage
     */
    public static MovieItem removeMovie(int id){
        return movieMap.remove(id);
    }

    /**
     * User rates the movie
     * @param mov MovieItem movie that was rated
     * @param rating double of rating
     */
    public static void rateMovie(MovieItem mov, double rating, Response.Listener<String> rl, Response.ErrorListener el){
        //MovieItem m = getMovie(mov.getID());

        MovieItem m = CurrentMovie.getMovie();

        //Set general ratings
        double oldavg;
        int oldnumrats;
        if (m != null){
            oldavg = m.getAverageRating();
            oldnumrats = m.getNumRatings();
        } else {
            oldavg = 0;
            oldnumrats = 0;
            m = mov;
        }
        double newavg = recalculateAverage(oldavg, oldnumrats, rating);
        m.setAverageRating(newavg);
        m.setNumRatings(oldnumrats + 1);

        //Set major specific ratings
        String major = CurrentUser.getProfile().getMajor();
        double oldMajorAvg = m.getMajorRating(major);
        int oldMajorRatings = m.getMajorCount(major);

        double newMajorAvg = recalculateAverage(oldMajorAvg, oldMajorRatings, rating);
        m.setMajorCount(major, oldMajorRatings + 1);
        m.setMajorRating(major, newMajorAvg);


        addMovie(m, rl, el);
//        Log.i("Map: ", "Movies in map ----------------------------");
//        for(int id : movieMap.keySet()){
//            Log.i("id: ", String.format("%d", id));
//            Log.i("Name: ", movieMap.get(id).getName());
//            Log.i("Rating: ", String.format("%f", movieMap.get(id).getAverageRating()));
//            Log.i("------","--------");
//        }
    }


    /**
     * Re-calculates average of the ratings after user inputs a new one
     *
     */
    private static double recalculateAverage(double oldAvg, int ratingNum, double rating){
        return (oldAvg*ratingNum + rating)/(ratingNum + 1);
    }

    /**
     * Gives list of MovieItems for search results
     * @return List<MovieItem> movieitem list
     */
    public static List<MovieItem> getMovieList(){
        List<MovieItem> l = new ArrayList();
        for(int id : movieMap.keySet()){
            if(movieMap.get(id).getNumRatings() > 0)
                l.add(movieMap.get(id));
        }
        return l;
    }

}
