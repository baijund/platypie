package edu.gatech.cs2340.nochill.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.CurrentMovie;
import edu.gatech.cs2340.nochill.models.MovieItem;
import edu.gatech.cs2340.nochill.models.MovieList;
import edu.gatech.cs2340.nochill.models.Movies;

public class RecommendationActivity extends ActionBarActivity {

    /**
     * Movies in list of recommendations
     */
    private ListView moviesView;
    /**
     * Spinner to determine major
     */
    private Spinner majorSpinner;
    /**
     * Screen where recommendationActivity is created
     */
    private Context thisContext = this;

    /**
     * Creates Recommendation Activity screen
     * @param savedInstanceState creates screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        setTitle("Recommendations");

        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);

        moviesView = (ListView)findViewById(R.id.recommendListView);
        majorSpinner.setEnabled(false);
        sortMajorRating("");

        moviesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                final Object o = moviesView.getItemAtPosition(position);
                final MovieItem movie = (MovieItem) o;//As you are using Default String Adapter
                CurrentMovie.setMovie(movie);
                goToActivityDescription();
                //Toast.makeText(getBaseContext(), str.getActors().get(0), Toast.LENGTH_SHORT).show();
            }
        });

        final CheckBox majorBox = (CheckBox) findViewById(R.id.major);

        majorBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    majorSpinner.setEnabled(true);
                    sortMajorRating(majorSpinner.getSelectedItem().toString());
                } else {
                    majorSpinner.setEnabled(false);
                    sortMajorRating("");
                }
            }
        });

        majorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(majorSpinner.isEnabled()) {
                    sortMajorRating(adapterView.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * Goes to activity description
     */
    private void goToActivityDescription() {
        final Intent intent = new Intent(this, DescriptionActivity.class);
        startActivity(intent);
    }

    /**
     * Sorts by Megorrrrr's'sss's
     * @param major Major to be shown
     */
    private void sortMajorRating(final String major){

        final List<MovieItem> l = new ArrayList<>();

        final Response.Listener<String> rl = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);
                JSONObject res = null;
                JSONArray moviesJ = new JSONArray();
                JSONArray majorRatingsJ = new JSONArray();
                try{
                    res = new JSONObject(response);
                    moviesJ = res.getJSONArray("movies");
                    majorRatingsJ = res.getJSONArray("majorRatings");
                } catch(JSONException e) {
                    Log.i("Error: ", e.toString());
                }

                for (int i = 0; i < moviesJ.length(); i++){
                    try{
                        res = moviesJ.getJSONObject(i);
                        final String name = res.getString("name");
                        final int year = res.getInt("year");
                        final String ratingMpaa = res.getString("rating_mpaa");
                        final int id = res.getInt("ID");
                        final String description = res.getString("description");
                        final double averageRating = res.getDouble("averageRating");
                        final int numRatings = res.getInt("numRatings");
                        l.add(new MovieItem(name, year, ratingMpaa, id, description, averageRating, numRatings, new ArrayList<String>()));
                    } catch (JSONException e){
                        Log.i("Error: ", e.toString());
                    }

                }
                final String s = "";
                if (!s.equals(major)){
                    for(int i = l.size() - 1; i >=0; i--){
                        final MovieItem m = l.get(i);
                        if(m.getMajorCount(major) == 0){
                            l.remove(i);
                        }
                    }

                    Collections.sort(l, new Comparator<MovieItem>() {
                        @Override
                        public int compare(MovieItem movieItem, MovieItem t1) {
                            return (movieItem.getMajorRating(major) > t1.getMajorRating(major))?-1:1;
                        }
                    });
                } else {

                    Collections.sort(l, new Comparator<MovieItem>() {
                        @Override
                        public int compare(MovieItem movieItem, MovieItem t1) {
                            return (movieItem.getAverageRating() > t1.getAverageRating())?-1:1;
                        }
                    });
                }

                moviesView.setAdapter(new MovieList(thisContext, R.layout.movie_item, l));
            }
        };

        final Response.ErrorListener el = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("getUser error: ", error.toString());
            }
        };

        Movies.getMovieList(rl, el);


    }

}
