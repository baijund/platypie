package edu.gatech.cs2340.nochill.models;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alaap on 2/13/16.
 */
public class Users {

    private static List<Profile> userList = new LinkedList<Profile>();

    /**
     * Adds user
     * @param p user to add
     * @param rl response listener
     * @param el error listener
     */
    public static void addUser(Profile p, Response.Listener<String> rl, Response.ErrorListener el){
        Log.i("addUser: ", "Attempting to add " + p.getUsername());
        UserRequester.addUser(p, rl, el);
    }

    /**
     * Edits old profile
     * @param p user to edit
     * @param rl response listener
     * @param el error listener
     */
    public static void editUser(Profile p, Response.Listener<String> rl, Response.ErrorListener el){
        UserRequester.editUser(p, rl, el);
    }

    /**
     * Returns true if successfully removed user or else returns false
     * @param username
     * @return if user was removed
     */
//    public static boolean removeUser(String username){
//        Profile p = getUser(username);
//        return userList.remove(p);
//    }

    /**
     * Gets user
     * @param username of user
     * @param rl1 response listener
     * @param el1 error listener
     */
    public static void getUser(String username, Response.Listener<String> rl1, Response.ErrorListener el1){

        final Response.Listener rl = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Log.i("getUser response: ", response.toString());
            }
        };

        final Response.ErrorListener el = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("getUser error: ", error.toString());
            }
        };

        UserRequester.getUser(username, rl, el);
    }

    /**
     * Login for user
     * @param username of user
     * @param password o fuser
     * @param rl response listener
     * @param el error listener
     */
    public static void login(String username, String password, Response.Listener<String> rl, Response.ErrorListener el){
        Log.i("Login: ", "Attempting login");
        UserRequester.login(username, password, rl, el);
    }


    /**
     *
     * @param username Username to be banned.
     * @return true if successful. False if user does not exist.
     */
    public static boolean banUser(String username){
//        Profile p = getUser(username);
//        if (p == null){
//            return false;
//        }
//        p.ban();
//        Log.i("Ban: ", "Banned " + username);
//        editUser(p);
        return true;
    }

    /**
     *
     * @param username Username to be unbanned.
     * @return true if successful. False if user does not exist.
     */
    public static boolean unbanUser(String username){
//        Profile p = getUser(username);
//        if (p == null){
//            return false;
//        }
//        p.unban();
//        editUser(p);
        return true;
    }

    /**
     *
     * @param username Username to be made admin.
     * @return true if successful. False if user does not exist.
     */
    public static boolean makeAdmin(String username){
//        Profile p = getUser(username);
//        if (p == null){
//            return false;
//        }
//        p.setAdmin(true);
//        editUser(p);
        return true;
    }

    /**
     *
     * @param username Username to take admin privileges from.
     * @return true if successful. False if user does not exist.
     */
    public static boolean unadmin(String username){
//        Profile p = getUser(username);
//        if (p == null){
//            return false;
//        }
//        p.setAdmin(false);
//        editUser(p);
        return true;
    }

//    public static List<String> getUserList(){
//        List<String> userListStrings = new ArrayList<>();
//        for(Profile p : userList){
//            userListStrings.add(p.getUsername());
//        }
//        return userListStrings;
//    }

    /**
     *
     * @return list of banned users except superadmin
     */
    public static List<String> getBannedUsers(){
        final List<String> bannedUsers = new ArrayList<>();
        for(final Profile p : userList){
            if(p.isBanned() && !p.isSuperAdmin()) {
                bannedUsers.add(p.getUsername());
            }
        }
        return bannedUsers;
    }

    /**
     *
     * @return list of unbanned users except superadmin
     */
    public static List<String> getUnbannedUsers(){
        final List<String> unbannedUsers = new ArrayList<>();
        for(final Profile p : userList){
            if(!p.isBanned() && !p.isSuperAdmin()) {
                unbannedUsers.add(p.getUsername());
            }
        }
        return unbannedUsers;
    }



}
