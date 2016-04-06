package edu.gatech.cs2340.nochill.models;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Baijun on 3/29/2016.
 */
public class UserRequester {

    private static RequestQueue queue = Requests.getQueue();

    /**
     * get user request
     * @param username of user
     * @param rl response listener
     * @param el error listener
     */
    public static void getUser(final String username, Response.Listener<String> rl, Response.ErrorListener el){
        final StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/users/getUser", rl, el){
            @Override
            protected Map<String,String> getParams(){
                final Map<String,String> params = new HashMap<String, String>();
                params.put("username",username);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }


    /**
     * Add user to requests
     * @param p profile of user
     * @param rl response listener
     * @param el error listener
     */
    public static void addUser(final Profile p, Response.Listener<String> rl, Response.ErrorListener el){
        final StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/users/addUser", rl, el){
            @Override
            protected Map<String,String> getParams(){
                final String username = p.getUsername();
                final String password = p.getPassword();
                final String about = "none";
                final String email = p.getEmail();
                final String major = p.getMajor();
                final String firstName = p.getFirstName();
                final String lastName = p.getLastName();
                final JSONObject j = new JSONObject();
                try {
                    j.put("firstName", firstName);
                    j.put("lastName", lastName);
                    j.put("email", email);
                    j.put("username", username);
                    j.put("major", major);
                    j.put("password", password);
                    j.put("about", about);
                } catch (JSONException e){
                    Log.i("addUSer Error: ", "get param error");
                }
                Log.i("PARAMETERS: ", j.toString());
                final Map<String,String> params = new HashMap<String, String>();
                params.put("userObjectString",j.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    /**
     * User login
     * @param username of user to login
     * @param password of user to log in
     * @param rl response listener
     * @param el error listener
     */
    public static void login(final String username, final String password, Response.Listener<String> rl, Response.ErrorListener el){
        final StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/users/login", rl, el){
            @Override
            protected Map<String,String> getParams(){
                final Map<String,String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    /**
     * Edits user
     * @param p profile of user
     * @param rl response listener
     * @param el error listener
     */
    public static void editUser(final Profile p, Response.Listener<String> rl, Response.ErrorListener el){
        final StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/users/editUser", rl, el){
            @Override
            protected Map<String,String> getParams(){

                final String username = p.getUsername();
                final String password = p.getPassword();
                final String about = p.getAboutMe();
                final String email = p.getEmail();
                final String major = p.getMajor();
                final String firstName = p.getFirstName();
                final String lastName = p.getLastName();
                final JSONObject j = new JSONObject();
                try {
                    j.put("firstName", firstName);
                    j.put("lastName", lastName);
                    j.put("email", email);
                    j.put("username", username);
                    j.put("major", major);
                    j.put("password", password);
                    j.put("about", about);
                    j.put("password", CurrentUser.getProfile().getPassword());
                } catch (Exception e){
                    Log.i("editUser Error: ", "param error");
                }
                Log.i("PARAMETERS: ", j.toString());
                final Map<String,String> params = new HashMap<String, String>();
                params.put("userObjectString",j.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

}
