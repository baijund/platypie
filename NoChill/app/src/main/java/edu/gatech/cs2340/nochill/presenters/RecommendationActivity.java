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

        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);



        moviesView = (ListView)findViewById(R.id.recommendListView);
        majorSpinner.setEnabled(false);
        sortMajorRating("");


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
            for(int i = l.size() - 1; i >=0; i--){
                MovieItem m = l.get(i);
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

        moviesView.setAdapter(new MovieList(this, R.layout.movie_item, l));


    }

}
