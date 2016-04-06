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

    private MovieItem movie;
    private TextView ratingtxt;
    private TextView actorsText;
    private TextView synopsisText;
    private TextView mpaaText;
    private TextView majorLabel;
    private TextView majorRatingText;

    /**
     * Creates screen
     * @param savedInstanceState screen
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
        final List<String> actorsList = movie.getActors();
        for (final String s : actorsList){
            actors += s + ", ";
        }
        actorsText.setText(actors);
        ratingtxt = (TextView) findViewById(R.id.ratingtxt);
        //ratingtxt.setText(String.format("%.2f", movie.getAverageRating()));
        mpaaText = (TextView) findViewById(R.id.mpaaText);
        mpaaText.setText(String.valueOf(movie.getratingMpaa()));
        updateFields();
        final Button rateButton = (Button) findViewById(R.id.rateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RatingBar mBar = (RatingBar) findViewById(R.id.ratingBar);
                final double rating = mBar.getRating();
                final Response.Listener<String> rl = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response to rate: ", response);
                        movie = CurrentMovie.getMovie();
                        updateFields();
                        Log.i("Ratebutton: ", "Clicked");
                        Log.i("CurrentMovie: ", CurrentMovie.getMovie().getName());
                        Log.i("Major Count: ", String.format("%d",CurrentMovie.getMovie().getMajorCount(CurrentUser.getProfile().getMajor())));
                    }
                };
                final Response.ErrorListener el = new Response.ErrorListener() {
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
        final Response.Listener<String> rl = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject theRes = null;
                MovieItem m = null;
                try{
                    theRes = new JSONObject(response);
                    Log.i("Response: ", "The response is " + response);
                    JSONArray resPre = new JSONArray(theRes.getString("movie"));
                    JSONObject res = resPre.getJSONObject(0);
                    final String name = res.getString("name");
                    final int year = res.getInt("year");
                    final String ratingMpaa = res.getString("rating_mpaa");
                    final int id = res.getInt("ID");
                    final String description = res.getString("description");
                    final double averageRating = res.getDouble("averageRating");
                    final int numRatings = res.getInt("numRatings");
                    final JSONArray jact = res.getJSONArray("actors");
                    final List<String> actors = new ArrayList<>();
                    final JSONArray jMajors = theRes.getJSONArray("majorRatings");

                    for(int i = 0; i < jact.length(); i++){
                        actors.add((String)jact.get(i));
                    }
                    m = new MovieItem(name, year, ratingMpaa, id, description, averageRating, numRatings, actors);

                    for(int i = 0; i < jMajors.length() - 1; i++){
                        m.setMajorCount(jMajors.getJSONObject(i).getString("major"), jMajors.getJSONObject(i).getInt("count"));
                        m.setMajorRating(jMajors.getJSONObject(i).getString("major"), jMajors.getJSONObject(i).getDouble("rating"));
                    }

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

        final Response.ErrorListener el = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("getUser error: ", error.toString());
            }
        };


        Movies.getMovie(CurrentMovie.getMovie().getID(), rl, el);


    }

}
