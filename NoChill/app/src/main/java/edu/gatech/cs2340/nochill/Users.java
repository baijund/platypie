package edu.gatech.cs2340.nochill;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alaap on 2/13/16.
 */
public class Users {

    private static List<Profile> userList = new ArrayList<Profile>();

    public static void addUser(Profile p){
        userList.add(p);
        Log.i("Users", "User "+ p.getUsername() +" has been added.");
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
