package edu.gatech.cs2340.nochill.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.nochill.models.CurrentMovie;
import edu.gatech.cs2340.nochill.models.MovieItem;
import edu.gatech.cs2340.nochill.models.MovieList;
import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.MovieRequester;
import edu.gatech.cs2340.nochill.models.Movies;

public class NewDVDActivity extends ActionBarActivity {

    Context thisContext = this;

    /**
     * Creates list for new DVDs
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dvd);
        setTitle("New DVDs");

        final List l = new ArrayList<MovieItem>();
        MovieRequester.requestNewReleases(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                Log.i("REQUEST THING", "Success");

                try {
                    JSONObject jsonRootObject = new JSONObject(response);
                    JSONArray jsonMoviesArray = jsonRootObject.optJSONArray("movies");
                    for (int i = 0; i < jsonMoviesArray.length(); i++) {
                        final JSONObject j = jsonMoviesArray.getJSONObject(i);
                        Log.i("Movie title: ", j.getString("title"));

                        final int id = j.getInt("id");
                        final String description = j.getString("synopsis");

                        final List<String> actors = new ArrayList<String>();
                        final JSONArray actorsArr = j.getJSONArray("abridged_cast");
                        for (int k = 0; k < actorsArr.length(); k++) {
                            JSONObject actorobj = actorsArr.getJSONObject(k);
                            actors.add(actorobj.getString("name"));
                        }

                        Response.Listener<String> rl = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                MovieItem m = null;
                                JSONObject res = null;
                                try {
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
                                    for (int i = 0; i < jact.length(); i++) {
                                        actors.add((String) jact.get(i));
                                    }
                                    m = new MovieItem(name, year, rating_mpaa, id, description, averageRating, numRatings, actors);
                                } catch (Exception e) {
                                    Log.i("Error: ", e.toString());
                                }

                                double averageRating;
                                int numRatings;
                                if (m != null) {
                                    averageRating = m.getAverageRating();
                                    numRatings = m.getNumRatings();
                                } else {
                                    averageRating = 0;
                                    numRatings = 0;
                                }
                                try {
                                    l.add(new MovieItem(j.getString("title"), j.getInt("year"), j.getString("mpaa_rating"), id, description, averageRating, numRatings, actors));
                                } catch (Exception e) {
                                    Log.i("Error: ", e.toString());
                                }

                                final ListView lv = (ListView) findViewById(R.id.movieList);
                                lv.setAdapter(new MovieList(thisContext, R.layout.movie_item, l));
                                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> parent, View view,
                                                            int position, long id) {

                                        Object o = lv.getItemAtPosition(position);
                                        MovieItem movie = (MovieItem) o;//As you are using Default String Adapter
                                        CurrentMovie.setMovie(movie);
                                        goToActivityDescription();

                                        //Toast.makeText(getBaseContext(), str.getActors().get(0), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        };

                        Response.ErrorListener el = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        };

                        Movies.getMovie(id, rl, el);
                    }

                } catch (JSONException e) {
                    Log.i("Error: ", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("REQUEST THING", "IT DIDNT RESPOND");
            }
        });





    }
    /**
     * Goes to activity description
     */
    private void goToActivityDescription() {
        Intent intent = new Intent(this, DescriptionActivity.class);
        startActivity(intent);
    }

}
