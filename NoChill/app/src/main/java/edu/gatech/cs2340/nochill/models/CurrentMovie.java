package edu.gatech.cs2340.nochill.models;

import com.android.volley.Response;

/**
 * Created by Baijun on 2/27/2016.
 */
public class CurrentMovie {

    private static MovieItem movie;

    /**
     * sets current movie
     * @param m
     */
    public static void setMovie(MovieItem m){
        movie = m;
    }

    /**
     * Gets current movie
     * @return current movie
     */
    public static MovieItem getMovie(){
        return movie;
    }

    /**
     * takes rating for movie
     * @param rating
     */
    public static void rate(double rating, Response.Listener<String> rl, Response.ErrorListener el){
        Movies.rateMovie(movie, rating, rl, el);
        //movie = Movies.getMovie(movie.getID());
        //movie.setAverageRating(Movies.getMovie(movie.getID()).getAverageRating());
    }

}
