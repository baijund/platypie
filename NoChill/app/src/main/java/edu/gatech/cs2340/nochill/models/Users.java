package edu.gatech.cs2340.nochill.models;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
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

        Response.Listener rl = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Log.i("getUser response: ", response.toString());
            }
        };

        Response.ErrorListener el = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("getUser error: ", error.toString());
            }
        };

        UserRequester.getUser(username, rl, el);

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
        Log.i("Ban: ", "Banned " + username);
        editUser(p);
        return true;
    }

    /**
     *
     * @param username Username to be unbanned.
     * @return true if successful. False if user does not exist.
     */
    public static boolean unbanUser(String username){
        Profile p = getUser(username);
        if (p == null){
            return false;
        }
        p.unban();
        editUser(p);
        return true;
    }

    /**
     *
     * @param username Username to be made admin.
     * @return true if successful. False if user does not exist.
     */
    public static boolean makeAdmin(String username){
        Profile p = getUser(username);
        if (p == null){
            return false;
        }
        p.setAdmin(true);
        editUser(p);
        return true;
    }

    /**
     *
     * @param username Username to take admin privileges from.
     * @return true if successful. False if user does not exist.
     */
    public static boolean unadmin(String username){
        Profile p = getUser(username);
        if (p == null){
            return false;
        }
        p.setAdmin(false);
        editUser(p);
        return true;
    }

    public static List<String> getUserList(){
        List<String> userListStrings = new ArrayList<>();
        for(Profile p : userList){
            userListStrings.add(p.getUsername());
        }
        return userListStrings;
    }

    /**
     *
     * @return list of banned users except superadmin
     */
    public static List<String> getBannedUsers(){
        List<String> bannedUsers = new ArrayList<>();
        for(Profile p : userList){
            if(p.isBanned() && !p.isSuperAdmin())
                bannedUsers.add(p.getUsername());
        }
        return bannedUsers;
    }

    /**
     *
     * @return list of unbanned users except superadmin
     */
    public static List<String> getUnbannedUsers(){
        List<String> unbannedUsers = new ArrayList<>();
        for(Profile p : userList){
            if(!p.isBanned() && !p.isSuperAdmin())
                unbannedUsers.add(p.getUsername());
        }
        return unbannedUsers;
    }

}
