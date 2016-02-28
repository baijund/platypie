package edu.gatech.cs2340.nochill.models;

/**
 * Created by Baijun on 2/27/2016.
 */
public class CurrentMovie {

    private static MovieItem movie;

    /**
     *
     * @param m
     */
    public static void setMovie(MovieItem m){
        movie = m;
    }

    /**
     *
     * @param rating
     */
    public static void rate(int rating){
        Movies.rateMovie(movie.getID(), rating);
    }

}
