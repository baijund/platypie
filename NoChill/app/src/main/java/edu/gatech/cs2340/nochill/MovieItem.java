package edu.gatech.cs2340.nochill;

/**
 * Created by Baijun on 2/19/2016.
 */
public class MovieItem {
    private String name;
    private int year;
    private String rating;

    public MovieItem(String name, int year, String rating) {
        this.name = name;
        this.year = year;
        this.rating = rating;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
