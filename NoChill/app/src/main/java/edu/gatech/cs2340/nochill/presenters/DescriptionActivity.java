package edu.gatech.cs2340.nochill.presenters;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.CurrentMovie;
import edu.gatech.cs2340.nochill.models.CurrentUser;
import edu.gatech.cs2340.nochill.models.MovieItem;
import edu.gatech.cs2340.nochill.models.Movies;

public class DescriptionActivity extends ActionBarActivity {

    MovieItem movie;
    TextView ratingtxt;
    TextView actorsText;
    TextView synopsisText;
    TextView mpaaText;
    TextView majorLabel;
    TextView majorRatingText;

    /**
     * Creates description
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        //Set the current movie
        movie = CurrentMovie.getMovie();
        setTitle(movie.getName());

        actorsText = (TextView) findViewById(R.id.actorsText);

        majorRatingText = (TextView) findViewById(R.id.majorRatingTxt);



        majorLabel = (TextView) findViewById(R.id.majorLabel);
        majorLabel.setText(CurrentUser.getProfile().getMajor());

//        TextView titleText = (TextView) findViewById(R.id.movieTitle);
//        titleText.setText(movie.getName());

        synopsisText = (TextView) findViewById(R.id.synopsisText);
        synopsisText.setText(movie.getDescription());

        String actors = "";
        List<String> actorsList = movie.getActors();
        for (String s : actorsList){
            actors += s + ", ";
        }

        actorsText.setText(actors);

        ratingtxt = (TextView) findViewById(R.id.ratingtxt);
        //ratingtxt.setText(String.format("%.2f", movie.getAverageRating()));

        mpaaText = (TextView) findViewById(R.id.mpaaText);
        mpaaText.setText(String.valueOf(movie.getRating_mpaa()));


        updateFields();

        Button rateButton = (Button) findViewById(R.id.rateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingBar mBar = (RatingBar) findViewById(R.id.ratingBar);
                double rating = mBar.getRating();
                CurrentMovie.rate(rating);
                movie = CurrentMovie.getMovie();
                updateFields();
                Log.i("Ratebutton: ", "Clicked");
                Log.i("CurrentMovie: ", CurrentMovie.getMovie().getName());
                Log.i("Major Count: ", String.format("%d",CurrentMovie.getMovie().getMajorCount(CurrentUser.getProfile().getMajor())));

            }
        });



    }

    /**
     * Updates fields for average rating and major specific rating
     */
    private void updateFields(){

        MovieItem m = Movies.getMovie(CurrentMovie.getMovie().getID());
        if(m == null){
            return;
        }

        if(m.getNumRatings() != 0){
            ratingtxt.setText(String.format("%.2f", m.getAverageRating()));
        }

        if(m.getMajorCount(CurrentUser.getProfile().getMajor()) != 0){
            majorRatingText.setText(String.format("%.2f", m.getMajorRating(CurrentUser.getProfile().getMajor())));
        } else {
            Log.i("MAJOR COUNT","waht ZEROOOO");
        }
    }

}
