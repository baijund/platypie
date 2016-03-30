package edu.gatech.cs2340.nochill.models;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

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


    public static void addUser(final Profile p, Response.Listener<String> rl, Response.ErrorListener el){
        StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/users/addUser", rl, el){
            @Override
            protected Map<String,String> getParams(){
                String username = p.getUsername();
                String password = p.getPassword();
                String about = "none";
                String email = p.getEmail();
                String major = p.getMajor();
                String firstName = p.getFirstName();
                String lastName = p.getLastName();
                JSONObject j = new JSONObject();
                try {
                    j.put("firstName", firstName);
                    j.put("lastName", lastName);
                    j.put("email", email);
                    j.put("username", username);
                    j.put("major", major);
                    j.put("password", password);
                    j.put("about", about);
                } catch (Exception e){
                    Log.i("addUSer Error: ", "get param error");
                }
                Log.i("PARAMETERS: ", j.toString());
                Map<String,String> params = new HashMap<String, String>();
                params.put("userObjectString",j.toString());
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

    public static void login(final String username, final String password, Response.Listener<String> rl, Response.ErrorListener el){
        StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/users/login", rl, el){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
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


    public static void editUser(final Profile p, Response.Listener<String> rl, Response.ErrorListener el){
        StringRequest sr = new StringRequest(Request.Method.POST, "https://nochill.herokuapp.com/users/editUser", rl, el){
            @Override
            protected Map<String,String> getParams(){

                String username = p.getUsername();
                String password = p.getPassword();
                String about = p.getAboutMe();
                String email = p.getEmail();
                String major = p.getMajor();
                String firstName = p.getFirstName();
                String lastName = p.getLastName();
                JSONObject j = new JSONObject();
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
                Map<String,String> params = new HashMap<String, String>();
                params.put("userObjectString",j.toString());
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
