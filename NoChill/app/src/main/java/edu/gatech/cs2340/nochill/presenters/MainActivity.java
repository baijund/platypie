package edu.gatech.cs2340.nochill.presenters;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.*;

import edu.gatech.cs2340.nochill.models.MovieRequester;
import edu.gatech.cs2340.nochill.models.Profile;
import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.Requests;
import edu.gatech.cs2340.nochill.models.Users;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates the request queue
        Requests.initializeRequestQueue(this);

        //HARDCODED ADMIN COMMENT OUT BEFORE DEMO
        Profile ADMIN = new Profile("admin","adminlast","adminemail","admin","password","Physics");
        Users.addUser(ADMIN);

        Button button = (Button) findViewById(R.id.button);
        Button register = (Button) findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegistration();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
     * Goes to login screen if user presses login
     */
    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to registration screen if user presses register
     */
    private void goToRegistration() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}