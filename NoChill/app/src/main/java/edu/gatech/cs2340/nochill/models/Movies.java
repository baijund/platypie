package edu.gatech.cs2340.nochill.models;

import android.util.Log;

import java.util.HashMap;

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
     *
     * @param id
     * @param rating
     */
    public static void rateMovie(int id, int rating){
        MovieItem m = getMovie(id);
        double oldavg = m.getAverageRating();
        int oldnumrats = m.getNumRatings();
        double newavg = (oldavg*oldnumrats + rating)/(oldnumrats + 1);
        m.setAverageRating(newavg);
        m.setNumRatings(oldnumrats + 1);
        addMovie(m);
    }


}
