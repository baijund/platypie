package edu.gatech.cs2340.nochill;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
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

import edu.gatech.cs2340.nochill.models.Movies;

public class SearchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //Creates the request queue
        Movies.initializeRequestQueue(this);

        Button searchButt = ((Button) findViewById(R.id.searchButton));


        searchButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doQuery();
            }
        });

    }

    private void doQuery() {

        EditText queryEditText = (EditText) findViewById(R.id.searchBar);
        String q = queryEditText.getText().toString();
        final List l = new ArrayList<MovieItem>();

        Movies.query(q, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                Log.i("REQUEST THING", "Success");

                try {
                    JSONObject jsonRootObject = new JSONObject(response);
                    JSONArray jsonMoviesArray = jsonRootObject.optJSONArray("movies");
                    for (int i = 0; i < jsonMoviesArray.length(); i++) {
                        JSONObject j = jsonMoviesArray.getJSONObject(i);
                        Log.i("Movie : ", j.getString("title") + j.getInt("year") + j.getString("mpaa_rating"));

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

        ListView lv = (ListView)findViewById(R.id.titleList);
        lv.setAdapter(new MovieList(this, R.layout.movie_item, l));

    }

}
