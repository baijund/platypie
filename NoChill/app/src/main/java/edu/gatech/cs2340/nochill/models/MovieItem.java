package edu.gatech.cs2340.nochill.models;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Baijun on 2/19/2016.
 */
public class MovieItem {
    private String name;
    private int year;
    private String rating_mpaa;
    private int ID;
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
        return ID;
    }

    /**
     * sets movie ID
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * gets synopsis of movie
     * @return description of movie
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets description of movie
     * @param description
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
     * sets average rating of the movie
     * @param averageRating
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
     * creates/sets list of actors
     * @param actors
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
     * @param numRatings
     */
    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public MovieItem(String name, int year, String rating, int id, String description, double averageRating, int numRatings, List<String> actors) {
        this.name = name;
        this.year = year;
        this.rating_mpaa = rating;
        ID = id;

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
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the year the movie was made
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * sets the year the movie was made
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * gets the MPAA rating of the movie
     * @return MPAA rating of the movie
     */
    public String getRating_mpaa() {
        return rating_mpaa;
    }

    /**
     * Set MPAA rating
     * @param rating_mpaa
     */
    public void setRating_mpaa(String rating_mpaa) {
        this.rating_mpaa = rating_mpaa;
    }

    public double getMajorRating(String major){
        Double rating = majorRatingMap.get(major);
        return rating!=null?rating:0;
    }

    public void setMajorRating(String major, double rating){
        majorRatingMap.put(major, rating);
    }

    public int getMajorCount(String major){
        Integer count = majorCountMap.get(major);
        return count!=null?count:0;
    }

    public void setMajorCount(String major, int rating){
        majorCountMap.put(major, rating);
    }

}
