package edu.gatech.cs2340.nochill;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import edu.gatech.cs2340.nochill.models.Movies;

public class NewReleases extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_releases);


        //Initiate the request queue
        Movies.initializeRequestQueue(this);



    }

}
