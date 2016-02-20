package edu.gatech.cs2340.nochill.models;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Baijun on 2/19/2016.
 */
public class Movies {
    private static String API_KEY = "yedukp76ffytfuy24zsqk7f5";
    private static String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0";
    private static String NewReleaseURL = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey="+API_KEY;

    // Instantiate the RequestQueue.
    private static RequestQueue queue;
    static String url ="http://www.google.com";


    public static void initializeRequestQueue(Context c){
        queue = Volley.newRequestQueue(c);
    }

    public static void dontUseThis(Context c){
        queue = Volley.newRequestQueue(c);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("REQUEST THING", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("REQUEST THING", "IT DIDNT RESPOND");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public static void requestNewReleases(Response.Listener<String> rl, Response.ErrorListener el){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, NewReleaseURL, rl, el);
        queue.add(stringRequest);
    }



}
