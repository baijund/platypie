package edu.gatech.cs2340.nochill.models;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Baijun on 2/19/2016.
 */
public class Movies {
    private static final String API_KEY = "yedukp76ffytfuy24zsqk7f5";
    private static final String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0";
    private static final String NewReleaseURL = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey="+API_KEY;
    private static final String InTheatersURL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey="+API_KEY;

    // Instantiate the RequestQueue.
    private static RequestQueue queue;
    static String url ="http://www.google.com";


    public static void initializeRequestQueue(Context c){
        queue = Volley.newRequestQueue(c);
    }

    public static void requestNewReleases(Response.Listener<String> rl, Response.ErrorListener el){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, NewReleaseURL, rl, el);
        queue.add(stringRequest);
    }

    public static void requestInTheater(Response.Listener<String> rl, Response.ErrorListener el){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, InTheatersURL, rl, el);
        queue.add(stringRequest);
    }

    public static void query(String q, Response.Listener<String> rl, Response.ErrorListener el){
        String url = "";
        try{
            url = String.format("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=%s&q=%s", API_KEY, URLEncoder.encode(q, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Log.i("Error: ", "Error encoding");
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, rl, el);
        queue.add(stringRequest);
    }


}
