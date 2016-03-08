package edu.gatech.cs2340.nochill.models;

import android.graphics.Movie;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Baijun on 2/27/2016.
 */
public class Movies {
    private static HashMap<Integer,MovieItem> movieMap = new HashMap<Integer,MovieItem>();

    /**
     * Returns movie associated with id
     * @param id id of movie as given by RT
     * @return MovieItem object if in storage or else null
     */
    public static MovieItem getMovie(int id){
        return movieMap.get(id);
    }

    /**
     * Adds movie to storage
     * @param m MovieItem to be added
     * @return null if movie did not exist or old MovieItem
     */
    public static MovieItem addMovie(MovieItem m){
        return movieMap.put(m.getID(), m);
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
    public static void rateMovie(MovieItem mov, double rating){
        MovieItem m = getMovie(mov.getID());

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


        addMovie(m);
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
