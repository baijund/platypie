package edu.gatech.cs2340.nochill.models;

import android.content.Context;

//import com.android.volley.Request;
import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


/**
 * Created by Baijun on 2/27/2016.
 */
public class Requests {

    private static RequestQueue queue;

    /**
     * Starts request queue
     * @param c context of queue
     */
    public static void initializeRequestQueue(Context c) {
        if (queue == null) {
            queue = Volley.newRequestQueue(c);
        }
    }

    /**
     * Queue for requests
     * @return RequestQueue queue of requests
     */
    public static RequestQueue getQueue(){
        return queue;
    }

}
