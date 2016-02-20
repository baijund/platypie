package edu.gatech.cs2340.nochill;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import edu.gatech.cs2340.nochill.models.Movies;

public class LoggedInActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        //Creates the request queue
        Movies.initializeRequestQueue(this);

        Button logout = ((Button) findViewById(R.id.logout));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });


        Button proButt = ((Button) findViewById(R.id.profileButton));
        proButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });

        Button releasesButton = ((Button) findViewById(R.id.releasesButton));
        releasesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Movies.requestNewReleases(new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("REQUEST THING", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("REQUEST THING", "IT DIDNT RESPOND");
                    }
                });
            }
        });

        Button dvdButt = ((Button) findViewById(R.id.dvdButton));
        dvdButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToDVD();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logged_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Goes to login screen
     */
    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to profile screen
     */
    private void goToProfile(){
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    private void goToDVD() {
        Intent intent = new Intent(this, NewDVDActivity.class);
        startActivity(intent);
    }
}
