package edu.gatech.cs2340.nochill.presenters;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.CurrentMovie;
import edu.gatech.cs2340.nochill.models.MovieItem;

public class descriptionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        MovieItem movie = CurrentMovie.getMovie();
        TextView titleText = (TextView) findViewById(R.id.movieTitle);
        titleText.setText(movie.getName());
    }

}
