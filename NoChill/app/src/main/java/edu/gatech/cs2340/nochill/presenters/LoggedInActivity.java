package edu.gatech.cs2340.nochill.presenters;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.Users;

public class LoggedInActivity extends ActionBarActivity {


    /**
     * Creates logged in screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        setTitle("Home");

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

        Button dvdButt = ((Button) findViewById(R.id.dvdButton));
        dvdButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToDVD();
            }

        });

        Button searchButt = ((Button) findViewById(R.id.searchButton));
        searchButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToSearch();
            }

        });

        Button newReleasesButt = ((Button) findViewById(R.id.releasesButton));
        newReleasesButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToNewReleases();
            }

        });

    }

    /**
     * Creates option menu for screen
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logged_in, menu);
        return true;
    }


    /**
     * Acts when item is selected
     * @param item
     * @return item selected
     */
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

    /**
     * Goes to DVD screen
     */
    private void goToDVD() {
        Intent intent = new Intent(this, NewDVDActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to search screen
     */
    private void goToSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to new releases screen
     */
    private void goToNewReleases() {
        Intent intent = new Intent(this, NewReleasesActivity.class);
        startActivity(intent);
    }
}
