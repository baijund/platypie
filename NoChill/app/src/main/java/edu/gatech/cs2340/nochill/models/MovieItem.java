package edu.gatech.cs2340.nochill.models;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Baijun on 2/19/2016.
 */
public class MovieItem {
    private String name;
    private int year;
    private String ratingMpaa;
    private int id;
    private String description;
    private double averageRating;
    private int numRatings;
    private List<String> actors;
    private HashMap<String, Double> majorRatingMap = new HashMap<String, Double>();
    private HashMap<String, Integer> majorCountMap = new HashMap<String, Integer>();

    /**
     * gets movie ID
     * @return id of movie
     */
    public int getID() {
        return id;
    }

    /**
     * sets movie ID
     * @param id movie ID from Rotten Tomatoes
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * gets synopsis of movie
     * @return description of movie
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets movie descriptions
     * @param description main description for movie
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets average rating for the movie
     * @return average rating of the movie
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Sets average Rating of movie
     * @param averageRating previous average
     */
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * gets actors in a list
     * @return list of actors
     */
    public List<String> getActors() {
        return actors;
    }

    /**
     * Sets list of actors
     * @param actors list of actors
     */
    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    /**
     * gets number of ratings
     * @return number of ratings
     */
    public int getNumRatings() {
        return numRatings;
    }

    /**
     * sets total number of ratings
     * @param numRatings current number of ratings
     */
    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    /**
     * @param name of movie
     * @param year movie released
     * @param rating current movie rating
     * @param id from rotten tomatoes
     * @param description synopsis of movie
     * @param averageRating current average rating of movie
     * @param numRatings of ratings given to movie
     * @param actors list of actors in movie
     */
    public MovieItem(String name, int year, String rating, int id, String description, double averageRating, int numRatings, List<String> actors) {
        this.name = name;
        this.year = year;
        this.ratingMpaa = rating;
        this.id = id;

        this.description = description;

        this.averageRating = averageRating;
        this.numRatings = numRatings;

        this.actors = actors;



    }

    /**
     * gets the name of the movie
     * @return name of the movie
     */
    public String getName() {

        return name;
    }

    /**
     * sets the name of the movie
     * @param name of movie
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the year the movie was made
     * @return int year of movie
     */
    public int getYear() {
        return year;
    }

    /**
     * sets the year the movie was made
     * @param year movie was released
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * gets the MPAA rating of the movie
     * @return MPAA rating of the movie
     */
    public String getratingMpaa() {
        return ratingMpaa;
    }

    /**
     * Set MPAA rating
     * @param ratingMpaa appropriateness of movie
     */
    public void setRatingMpaa(String ratingMpaa) {
        this.ratingMpaa = ratingMpaa;
    }

    /**
     * Gets major rating of movie
     * @param major of user
     * @return double of rating
     */
    public double getMajorRating(String major){
        final Double rating = majorRatingMap.get(major);
        return rating!=null?rating:0;
    }

    /**
     * sets the major's rating
     * @param major String
     * @param rating current rating by major
     */
    public void setMajorRating(String major, double rating){
        majorRatingMap.put(major, rating);
    }

    /**
     * Gets number of people who rated movie from major
     * @param major of user
     * @return int number of people
     */
    public int getMajorCount(String major){
        final Integer count = majorCountMap.get(major);
        return count!=null?count:0;
    }

    /**
     * Sets number of people who rate movie from major
     * @param major of user
     * @param rating given to movie
     */
    public void setMajorCount(String major, int rating){
        majorCountMap.put(major, rating);
    }

}
