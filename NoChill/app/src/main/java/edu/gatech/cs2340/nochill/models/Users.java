package edu.gatech.cs2340.nochill.models;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alaap on 2/13/16.
 */
public class Users {

    private static List<Profile> userList = new LinkedList<Profile>();

    /**
     * Returns false if unable to add user or else returns true
     * @param p
     * @return if user was added
     */
    public static boolean addUser(Profile p){
        if (getUser(p.getUsername()) != null){
            return false;
        }
        userList.add(p);
        Log.i("Users", "User "+ p.getUsername() +" has been added.");
        return true;
    }

    /**
     * Removes old profile and adds new one
     * @param p
     */
    public static void editUser(Profile p){
        removeUser(p.getUsername());
        addUser(p);
    }

    /**
     * Returns true if successfully removed user or else returns false
     * @param username
     * @return if user was removed
     */
    public static boolean removeUser(String username){
        Profile p = getUser(username);
        return userList.remove(p);
    }

    /**
     * Returns the user's profile when a username is entered
     * @param username
     * @return user's profile
     */
    public static Profile getUser(String username){
        for(Profile p : userList){
            if (p.getUsername().equals(username)){
                return p;
            }
        }
        return null;
    }


    /**
     *
     * @param username Username to be banned.
     * @return true if successful. False if user does not exist.
     */
    public static boolean banUser(String username){
        Profile p = getUser(username);
        if (p == null){
            return false;
        }
        p.ban();
        editUser(p);
        return true;
    }

}
