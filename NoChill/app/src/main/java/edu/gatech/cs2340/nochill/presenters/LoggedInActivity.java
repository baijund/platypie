package edu.gatech.cs2340.nochill.presenters;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.CurrentUser;
//import edu.gatech.cs2340.nochill.models.Users;

public class LoggedInActivity extends ActionBarActivity {


    /**
     * creates screen
     * @param savedInstanceState screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        setTitle("Home");

        final Button logout = ((Button) findViewById(R.id.logout));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });


        final Button proButt = ((Button) findViewById(R.id.profileButton));
        proButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });

        final Button dvdButt = ((Button) findViewById(R.id.dvdButton));
        dvdButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToDVD();
            }

        });

        final Button searchButt = ((Button) findViewById(R.id.searchButton));
        searchButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToSearch();
            }

        });

        final Button newReleasesButt = ((Button) findViewById(R.id.releasesButton));
        newReleasesButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToNewReleases();
            }

        });

        final Button recommendationsButton = ((Button) findViewById(R.id.recommendationButton));
        recommendationsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToRecommendations();
            }

        });


        final Button adminProfileButton = ((Button) findViewById(R.id.adminProfileButton));
        adminProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToAdminChanges();
            }

        });

        if(!CurrentUser.getProfile().isAdmin()){
            adminProfileButton.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * creates options menu
     * @param menu initiates menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logged_in, menu);
        return true;
    }


    /**
     * Select options
     * @param item options menu
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();

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
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to profile screen
     */
    private void goToProfile(){
        final Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to DVD screen
     */
    private void goToDVD() {
        final Intent intent = new Intent(this, NewDVDActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to search screen
     */
    private void goToSearch() {
        final Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to new releases screen
     */
    private void goToNewReleases() {
        final Intent intent = new Intent(this, NewReleasesActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to new recommendations screen
     */
    private void goToRecommendations() {
        final Intent intent = new Intent(this, RecommendationActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to admin changes screen screen
     */
    private void goToAdminChanges() {
        if(CurrentUser.getProfile().isAdmin()){
            final Intent intent = new Intent(this, minActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Not an admin",
                    Toast.LENGTH_LONG).show();
        }
    }
}
