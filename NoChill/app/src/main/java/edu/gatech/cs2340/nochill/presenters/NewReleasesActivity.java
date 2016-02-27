package edu.gatech.cs2340.nochill.presenters;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.nochill.models.MovieItem;
import edu.gatech.cs2340.nochill.models.MovieList;
import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.Movies;

public class NewReleasesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_releases);


        //Initiate the request queue
        Movies.initializeRequestQueue(this);

        final List l = new ArrayList<MovieItem>();
        Movies.requestInTheater(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                Log.i("REQUEST THING", "Success");

                try {
                    JSONObject jsonRootObject = new JSONObject(response);
                    JSONArray jsonMoviesArray = jsonRootObject.optJSONArray("movies");
                    for (int i = 0; i < jsonMoviesArray.length(); i++) {
                        JSONObject j = jsonMoviesArray.getJSONObject(i);
                        Log.i("Movie title: ", j.getString("title"));

                        l.add(new MovieItem(j.getString("title"), j.getInt("year"), j.getString("mpaa_rating")));
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


        ListView lv = (ListView)findViewById(R.id.theaterReleasesList);
        lv.setAdapter(new MovieList(this, R.layout.movie_item, l));


    }

}
