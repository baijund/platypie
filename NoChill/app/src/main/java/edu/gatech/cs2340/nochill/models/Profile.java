package edu.gatech.cs2340.nochill.models;

//import android.os.Parcelable;
//import android.os.Parcel;

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
    private String major;
    private boolean banned;
    private boolean admin;
    private boolean superAdmin;

    /**
     * Creates profile object
     * @param firstName of user
     * @param lastName of user
     * @param email of user
     * @param username of user
     * @param password of user
     * @param major of user
     */
    public Profile(String firstName, String lastName, String email, String username, String password, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.major = major;
    }

    /**
     * Returns first name of user
     * @return first name of user
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Sets the first name of user
     * @param fname is the user's new first name
     */
    public void setFirstName(String fname){
        firstName = fname;
    }

    /**
     * Returns the user's last name
     * @return lastName
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Sets the user's new last name
     * @param lname last name of user
     */
    public void setLastName(String lname){lastName = lname;}

    /**
     * Returns the user's email
     * @return email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Sets the user's new email
     * @param em email of user
     */
    public void setEmail(String em){email = em;}

    /**
     * Returns the user's password
     * @return password
     */
    public String getPassword(){
        return password;
    }

    /**
     * Sets the user's new password
     * @param pword password of user
     */
    public void setPassword(String pword){password = pword;}

    /**
     * Returns the user's username
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * Returns the user's about me
     * @return aboutMe
     */
    public String getAboutMe(){ return aboutMe;}

    /**
     * Sets the user's new about me
     * @param about about me of user
     */
    public void setAboutMe(String about){aboutMe = about;}

    /**
     * Returns the user's about me
     * @return String representing profile major
     */
    public String getMajor(){
        return major;
    }

    /**
     * Sets the user's new about me
     * @param m major
     */
    public void setMajor(String m){
        major = m;
    }


    /**
     * bans user
     */
    public void ban(){
        banned = true;
    }

    /**
     * unbans user
     */
    public void unban(){
        banned = false;
    }

    /**
     * gets if user is banned or not
     * @return true if user is banned or else false
     */
    public boolean isBanned(){
        return banned;
    }

    /**
     * Gets if user is an admin
     * @return true if admin or else false
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets admin status.
     * @param admin true if making an admin or else false.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     *
     * @return true if superadmin or else false
     */
    public boolean isSuperAdmin() {
        return superAdmin;
    }

    /**
     *
     * @param superAdmin true if setting superadmin or else false.
     */
    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }
}
