package edu.gatech.cs2340.nochill;

/**
 * Created by Baijun on 2/14/2016.
 * This class holds information on the logged in user
 */
public class CurrentUser {

    private static boolean loggedIn;
    private static Profile profile;

    //Sets profile and logged in
    public static void login(Profile p){
        loggedIn = true;
        profile = p;
    }

    //Edits profile in storage and in current user
    public static void editProfile(Profile p){
        profile = p;
        Users.editUser(profile);
    }

    public static void logOut(){
        loggedIn = false;
    }

    public static boolean isLoggedIn(){
        return loggedIn;
    }

}
