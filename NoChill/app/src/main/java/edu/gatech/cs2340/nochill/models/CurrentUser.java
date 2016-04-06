package edu.gatech.cs2340.nochill.models;

import com.android.volley.Response;

/**
 * Created by Baijun on 2/14/2016.
 * This class holds information on the logged in user
 */
public final class CurrentUser {

    /**
     * Creates private constructor for currentuser
     */
    private CurrentUser() {

    }

    /**
     * State of being logged in
     */
    private static boolean loggedIn;
    /**
     * Current user's profile
     */
    private static Profile profile;

    /**
     * Sets profile and logged in
     * @param p profile
     */
    public static void login(Profile p){
        loggedIn = true;
        profile = p;
    }

    /**
     * Edits profile
     * @param p current profile
     * @param rl response listener
     * @param el error listener
     */
    public static void editProfile(Profile p, Response.Listener<String> rl, Response.ErrorListener el){
        profile = p;
        Users.editUser(profile, rl, el);
    }

    /**
     * logs out user
     */
    public static void logOut(){
        loggedIn = false;
    }

    /**
     * whether or not the user is logged in
     * @return boolean whether or not user is logged in
     */
    public static boolean isLoggedIn(){
        return loggedIn;
    }

    /**
     * returns profile of current user
     * @return current user profile
     */
    public static Profile getProfile(){
        return profile;
    }

}
