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

    // Instantiate the RequestQueue.
    private static RequestQueue queue;
    static String url ="http://www.google.com";


    public static void initiateRequestQueue(Context c){
        queue = Volley.newRequestQueue(c);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("REQUEST THING", "IT RESPONDED");
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

}
