package edu.gatech.cs2340.nochill.presenters;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import edu.gatech.cs2340.nochill.R;

public class adminActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setTitle("Admin Page");
    }

}
