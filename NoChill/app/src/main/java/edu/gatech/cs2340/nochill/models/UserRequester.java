package edu.gatech.cs2340.nochill.models;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Baijun on 3/29/2016.
 */
public class UserRequester {

    private static RequestQueue queue = Requests.getQueue();

    public static void getUser(final String username, Response.Listener<String> rl, Response.ErrorListener el){
        StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/users/getUser", rl, el){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

}
