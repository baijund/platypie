package edu.gatech.cs2340.nochill;

import android.os.Parcelable;
import android.os.Parcel;

/**
 * Created by Alaap on 2/13/16.
 */
public class Profile implements Parcelable {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    private Profile(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        username = in.readString();
        password = in.readString();
    }

    public Profile(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(firstName);
        out.writeString(lastName);
        out.writeString(email);
        out.writeString(username);
        out.writeString(password);
    }

    public static final Parcelable.Creator<Profile> CREATOR
            = new Parcelable.Creator<Profile>() {
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }
}
