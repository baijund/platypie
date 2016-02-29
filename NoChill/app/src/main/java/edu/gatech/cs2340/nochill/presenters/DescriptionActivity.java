package edu.gatech.cs2340.nochill.presenters;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.CurrentMovie;
import edu.gatech.cs2340.nochill.models.MovieItem;
import edu.gatech.cs2340.nochill.models.Movies;

public class DescriptionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        final MovieItem movie = CurrentMovie.getMovie();

        TextView titleText = (TextView) findViewById(R.id.movieTitle);
        titleText.setText(movie.getName());

        TextView synopsisText = (TextView) findViewById(R.id.synopsisText);
        synopsisText.setText(movie.getDescription());

        String actors = "";
        List<String> actorsList = movie.getActors();
        for (String s : actorsList){
            actors += s + ", ";
        }
        TextView actorsText = (TextView) findViewById(R.id.actorsText);
        actorsText.setText(actors);

        final TextView ratingtxt = (TextView) findViewById(R.id.ratingtxt);
        ratingtxt.setText(String.format("%.2f", movie.getAverageRating()));

        TextView mpaaText = (TextView) findViewById(R.id.mpaaText);
        mpaaText.setText(String.valueOf(movie.getRating_mpaa()));


        Button rateButton = (Button) findViewById(R.id.rateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingBar mBar = (RatingBar) findViewById(R.id.ratingBar);
                double rating = mBar.getRating();
                CurrentMovie.rate(rating);
                ratingtxt.setText(String.format("%.2f", movie.getAverageRating()));
            }
        });



    }


}
