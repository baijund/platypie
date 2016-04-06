package edu.gatech.cs2340.nochill.models;

//import android.app.DownloadManager;
//import android.content.Context;
//import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
//import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Baijun on 2/19/2016.
 */
public final class MovieRequester {
    /**
     * Creates private constructor for MovieRequester Object
     */
    private MovieRequester() {}

    /**
     * General API key
     */
    private static final String API_KEY = "7wj4raxmwrr475d3na6ujxbr";
    //private static final String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0";
    /**
     * URL to get new releases
     */
    private static final String NEW_RELEASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey="+API_KEY;
    /**
     * URL to get in theaters movies
     */
    private static final String IN_THEATERS_URL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey="+API_KEY;

    /**
     * Request queue of movies
     */
    private static RequestQueue queue = Requests.getQueue();

    /**
     * requests new releases
     * @param rl response listener
     * @param el error listener
     */
    public static void requestNewReleases(Response.Listener<String> rl, Response.ErrorListener el){
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, NEW_RELEASE_URL, rl, el);
        queue.add(stringRequest);
    }

    /**
     * requests theaters
     * @param rl response listener
     * @param el error listener
     */
    public static void requestInTheater(Response.Listener<String> rl, Response.ErrorListener el){
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, IN_THEATERS_URL, rl, el);
        queue.add(stringRequest);
    }

    /**
     * gets query
     * @param q query
     * @param rl response listener
     * @param el error listener
     */
    public static void query(String q, Response.Listener<String> rl, Response.ErrorListener el){
        String url = "";
        try{
            url = String.format("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=%s&q=%s", API_KEY, URLEncoder.encode(q, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Log.i("Error: ", "Error encoding");
        }
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, rl, el);
        queue.add(stringRequest);
    }


}
