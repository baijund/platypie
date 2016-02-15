package edu.gatech.cs2340.nochill;

import android.os.Parcelable;
import android.os.Parcel;

/**
 * Created by Alaap on 2/13/16.
 */
public class Profile {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String aboutMe;

    public Profile(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String fname){
        firstName = fname;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lname){lastName = lname;}

    public String getEmail(){
        return email;
    }
    public void setEmail(String em){email = em;}

    public String getPassword(){
        return password;
    }
    public void setPassword(String pword){password = pword;}

    public String getUsername(){
        return username;
    }

    public String getAboutMe(){ return aboutMe;}
    public void setAboutMe(String about){aboutMe = about;}
}
