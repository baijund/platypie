package edu.gatech.cs2340.nochill.models;

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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public int getNumRatings() {
        return numRatings;
    }

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

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating_mpaa() {
        return rating_mpaa;
    }

    public void setRating_mpaa(String rating_mpaa) {
        this.rating_mpaa = rating_mpaa;
    }
}
