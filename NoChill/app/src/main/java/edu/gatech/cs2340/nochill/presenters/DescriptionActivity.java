package edu.gatech.cs2340.nochill.presenters;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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
                final double rating = mBar.getRating();

                Response.Listener<String> rl = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response: ", response);
                        movie = CurrentMovie.getMovie();
                        updateFields();
                        Log.i("Ratebutton: ", "Clicked");
                        Log.i("CurrentMovie: ", CurrentMovie.getMovie().getName());
                        Log.i("Major Count: ", String.format("%d",CurrentMovie.getMovie().getMajorCount(CurrentUser.getProfile().getMajor())));
                    }
                };

                Response.ErrorListener el = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("rate error: ", error.toString());
                    }
                };


                CurrentMovie.rate(rating, rl, el);

            }
        });



    }

    /**
     * Updates fields for average rating and major specific rating
     */
    private void updateFields(){



        //MovieItem m = Movies.getMovie(CurrentMovie.getMovie().getID());

        Response.Listener<String> rl = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject res = null;
                MovieItem m = null;
                try{
                    res = new JSONObject(response);
                    String name = res.getString("name");
                    int year = res.getInt("year");
                    String rating_mpaa = res.getString("rating_mpaa");
                    int id = res.getInt("ID");
                    String description = res.getString("description");
                    double averageRating = res.getDouble("averageRating");
                    int numRatings = res.getInt("numRatings");
                    JSONArray jact = res.getJSONArray("actors");
                    List<String> actors = new ArrayList<>();
                    for(int i = 0; i < jact.length(); i++){
                        actors.add((String)jact.get(i));
                    }
                    m = new MovieItem(name, year, rating_mpaa, id, description, averageRating, numRatings, actors);
                } catch (Exception e){
                    Log.i("Error: ", e.toString());
                }


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
        };

        Response.ErrorListener el = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("getUser error: ", error.toString());
            }
        };


        Movies.getMovie(CurrentMovie.getMovie().getID(), rl, el);


    }

}
