package edu.gatech.cs2340.nochill.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

public class SearchActivity extends ActionBarActivity {
    /**
     * Creates screen for SearchActivity
     */
    private Context thisContext = this;

    /**
     * Creates movie search screen
     * @param savedInstanceState creates screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Button searchButt = ((Button) findViewById(R.id.searchButton));


        searchButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doQuery();
            }
        });
        setTitle("Search");

    }

    /**
     * Searches for movie
     */
    private void doQuery() {

        final EditText queryEditText = (EditText) findViewById(R.id.searchBar);
        final String q = queryEditText.getText().toString();
        final List<MovieItem> l = new ArrayList<MovieItem>();

        MovieRequester.query(q, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                Log.i("REQUEST THING", "Success");

                try {
                    final JSONObject jsonRootObject = new JSONObject(response);
                    final JSONArray jsonMoviesArray = jsonRootObject.optJSONArray("movies");
                    for (int i = 0; i < jsonMoviesArray.length(); i++) {
                        final JSONObject j = jsonMoviesArray.getJSONObject(i);
                        Log.i("Movie title: ", j.getString("title"));

                        final int id = j.getInt("id");
                        final String description = j.getString("synopsis");

                        final List<String> actors = new ArrayList<String>();
                        final JSONArray actorsArr = j.getJSONArray("abridged_cast");
                        for (int k = 0; k < actorsArr.length(); k++) {
                            final JSONObject actorobj = actorsArr.getJSONObject(k);
                            actors.add(actorobj.getString("name"));
                        }
                        l.add(new MovieItem(j.getString("title"), j.getInt("year"), j.getString("mpaa_rating"), id, description, 0, 0, actors));

                        final ListView lv = (ListView) findViewById(R.id.theaterReleasesList);
                        lv.setAdapter(new MovieList(thisContext, R.layout.movie_item, l));


                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {

                                final Object o = lv.getItemAtPosition(position);
                                final MovieItem movie = (MovieItem) o;//As you are using Default String Adapter
                                CurrentMovie.setMovie(movie);
                                goToActivityDescription();
                            }
                        });
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
        final Intent intent = new Intent(this, DescriptionActivity.class);
        startActivity(intent);
    }

}
