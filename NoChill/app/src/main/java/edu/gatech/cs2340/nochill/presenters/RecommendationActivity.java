package edu.gatech.cs2340.nochill.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;

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


    ListView moviesView;
    Spinner majorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        setTitle("Recommendations");

        List<MovieItem> filteredMovies = Movies.getMovieList();
        Collections.sort(filteredMovies, new Comparator<MovieItem>() {
            @Override
            public int compare(MovieItem movieItem, MovieItem t1) {
                return (int) Math.round(movieItem.getAverageRating() - t1.getAverageRating());
            }
        });

        moviesView = (ListView)findViewById(R.id.recommendListView);
        moviesView.setAdapter(new MovieList(this, R.layout.movie_item, filteredMovies));



        moviesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Object o = moviesView.getItemAtPosition(position);
                MovieItem movie = (MovieItem) o;//As you are using Default String Adapter
                CurrentMovie.setMovie(movie);
                goToActivityDescription();
                //Toast.makeText(getBaseContext(), str.getActors().get(0), Toast.LENGTH_SHORT).show();
            }
        });

        CheckBox majorBox = (CheckBox) findViewById(R.id.major);

        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);

        majorBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sortMajorRating(majorSpinner.getSelectedItem().toString());
                } else {
                    sortMajorRating("");
                }
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

    private void sortMajorRating(final String major){

        List<MovieItem> l = Movies.getMovieList();

        if (!major.equals("")){
            for(int i = 0; i < l.size(); i++){
                MovieItem m = l.get(i);
                if(m.getMajorCount(major) == 0){
                    l.remove(i);
                }
            }

            Collections.sort(l, new Comparator<MovieItem>() {
                @Override
                public int compare(MovieItem movieItem, MovieItem t1) {
                    return (int) Math.round(movieItem.getMajorRating(major) - t1.getMajorRating(major));
                }
            });
        } else {

            Collections.sort(l, new Comparator<MovieItem>() {
                @Override
                public int compare(MovieItem movieItem, MovieItem t1) {
                    return (int) Math.round(movieItem.getAverageRating() - t1.getAverageRating());
                }
            });
        }

        moviesView.setAdapter(new MovieList(this, R.layout.movie_item, l));


    }

}
