package edu.gatech.cs2340.nochill.models;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.nochill.R;

/**
 * Created by Baijun on 2/19/2016.
 */
public class MovieList extends ArrayAdapter<MovieItem> {
    private Activity context;
    private List<MovieItem> list;

    public MovieList(Context context, int resource, List<MovieItem> objects) {
        super(context, resource, R.id.movieItemName, objects);
        this.context = (Activity) context;
        this.list = objects;
    }

    /**
     * Gets proper view of the movies
     * @param position of movie in list
     * @param view object
     * @param parent object
     * @return
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.movie_item, null, true);

        TextView title = (TextView) rowView.findViewById(R.id.movieItemName);
        TextView rating = (TextView) rowView.findViewById(R.id.movieItemRating);
        TextView year = (TextView) rowView.findViewById(R.id.movieItemYear);

        title.setText(list.get(position).getName());
        rating.setText(list.get(position).getRating_mpaa());
        year.setText(String.valueOf(list.get(position).getYear()));

        return rowView;
    }
}
