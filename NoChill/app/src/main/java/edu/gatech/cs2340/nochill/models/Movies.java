package edu.gatech.cs2340.nochill.models;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

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
     * Gets movie from app
     * @param id rotten tomatoes id
     * @param rl response listener
     * @param el error listener
     */
    public static void getMovie(final Integer id, Response.Listener<String> rl, Response.ErrorListener el){
        final StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/movies/getMovie", rl, el){
            @Override
            protected Map<String,String> getParams(){

                final Map<String,String> params = new HashMap<String, String>();
                params.put("id",id.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    /**
     * Adds movie to list
     * @param m current movie item to add
     * @param rl responselistener
     * @param el error listener
     */
    public static void addMovie(final MovieItem m, Response.Listener<String> rl, Response.ErrorListener el){
        final StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/movies/addMovie", rl, el){
            @Override
            protected Map<String,String> getParams(){

                final String name = m.getName();
                final int id = m.getID();
                final int year = m.getYear();
                final String ratingMpaa = m.getratingMpaa();
                final String description = m.getDescription();
                final double averageRating = m.getAverageRating();
                final int numRatings = m.getNumRatings();
                final List<String> actors = m.getActors();

                final JSONObject j = new JSONObject();
                final JSONArray jArr = new JSONArray(actors);
                try {
                    j.put("name", name);
                    j.put("id", id);
                    j.put("year", year);
                    j.put("rating_mpaa", ratingMpaa);
                    j.put("description", description);
                    j.put("averageRating", averageRating);
                    j.put("numRatings", numRatings);
                    j.put("actors", jArr);
                    //j.put("actors", "[a1, a2, a3]");
                } catch (Exception e){
                    Log.i("addUSer Error: ", "get param error");
                }

                final JSONObject j2 = new JSONObject();
                try {
                    j2.put("major", CurrentUser.getProfile().getMajor());
                    j2.put("rating", 1);
                    j2.put("count", 1);
                    j2.put("id", id);
                } catch (Exception e){
                    Log.i("addUSer Error: ", "get param error");
                }
                Log.i("PARAMETERS: ", j.toString());
                final Map<String,String> params = new HashMap<String, String>();
                params.put("movieString",j.toString());
                params.put("majorRatingString",j2.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String,String> params = new HashMap<String, String>();
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
     * Rating movie
     * @param mov to rate
     * @param rating for movie
     * @param rl response listener
     * @param el error listener
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
        final double newavg = recalculateAverage(oldavg, oldnumrats, rating);
        m.setAverageRating(newavg);
        m.setNumRatings(oldnumrats + 1);

        //Set major specific ratings
        final String major = CurrentUser.getProfile().getMajor();
        final double oldMajorAvg = m.getMajorRating(major);
        final int oldMajorRatings = m.getMajorCount(major);

        final double newMajorAvg = recalculateAverage(oldMajorAvg, oldMajorRatings, rating);
        m.setMajorCount(major, oldMajorRatings + 1);
        m.setMajorRating(major, newMajorAvg);


        addMovie(m, rl, el);
    }


    /**
     * Recalculates average for movie
     * @param oldAvg previous average
     * @param ratingNum number rated
     * @param rating of movie
     * @return double
     */
    private static double recalculateAverage(double oldAvg, int ratingNum, double rating){
        return (oldAvg*ratingNum + rating)/(ratingNum + 1);
    }

    /**
     * Gets movie list
     * @param rl response listener
     * @param el error listener
     */
    public static void getMovieList(Response.Listener<String> rl, Response.ErrorListener el){

        final StringRequest sr = new StringRequest(Request.Method.GET, "https://nochill.herokuapp.com/movies/getMovieList", rl, el);
        queue.add(sr);

//        List<MovieItem> l = new ArrayList();
//        for(int id : movieMap.keySet()){
//            if(movieMap.get(id).getNumRatings() > 0)
//                l.add(movieMap.get(id));
//        }
//        return l;
    }

}
