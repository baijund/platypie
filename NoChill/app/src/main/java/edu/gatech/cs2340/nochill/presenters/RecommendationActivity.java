package edu.gatech.cs2340.nochill.presenters;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.nochill.R;

public class RecommendationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        setTitle("Recommendations");

        Button recommendationButton = ((Button) findViewById(R.id.recommendationButton));



    }

}
