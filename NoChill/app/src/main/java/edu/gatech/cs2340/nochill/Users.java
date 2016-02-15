package edu.gatech.cs2340.nochill;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alaap on 2/13/16.
 */
public class Users {

    private static List<Profile> userList = new LinkedList<Profile>();

    //Returns false if unable to add user or else returns true
    public static boolean addUser(Profile p){
        if (getUser(p.getUsername()) != null){
            return false;
        }
        userList.add(p);
        Log.i("Users", "User "+ p.getUsername() +" has been added.");
        return true;
    }

    //Removes old profile and adds new one
    public static void editUser(Profile p){
        removeUser(p.getUsername());
        addUser(p);
    }

    //Returns true if successfully removed user or else returns false
    public static boolean removeUser(String username){
        Profile p = getUser(username);
        return userList.remove(p);
    }

    public static Profile getUser(String username){
        for(Profile p : userList){
            if (p.getUsername().equals(username)){
                return p;
            }
        }
        return null;
    }

}
