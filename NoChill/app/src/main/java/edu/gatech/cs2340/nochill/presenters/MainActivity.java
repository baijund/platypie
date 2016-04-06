package edu.gatech.cs2340.nochill.presenters;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.net.CookieHandler;
import java.net.CookieManager;

import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.Requests;


public class MainActivity extends ActionBarActivity {

    /**
     * Creates screen
     * @param savedInstanceState creates screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Optionally, you can just use the default CookieManager
        final CookieManager manager = new CookieManager();
        CookieHandler.setDefault( manager  );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("NoChill");

        //Creates the request queue
        Requests.initializeRequestQueue(this);

//        //HARDCODED ADMIN COMMENT OUT BEFORE DEMO
//        Profile ADMIN = new Profile("admin","adminlast","adminemail","admin","password","Physics");
//        ADMIN.setSuperAdmin(true);
//        ADMIN.setAdmin(true);
//        Users.addUser(ADMIN);
//
//        Profile physics = new Profile("Physics","adminlast","adminemail","physics","pass","Physics");
//        Users.addUser(physics);
//
//        Profile math = new Profile("Math","adminlast","adminemail","math","pass","Applied Mathematics");
//        Users.addUser(math);
//
//        Profile biology = new Profile("Biology","adminlast","adminemail","biology","pass","Biology");
//        Users.addUser(biology);

        final Button button = (Button) findViewById(R.id.button);
        final Button register = (Button) findViewById(R.id.register);
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


    /**
     * Creates options menu
     * @param menu creates menu of options
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Selects an item
     * @param item selects item from movie
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
     * Goes to login screen if user presses login
     */
    private void goToLogin() {
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to registration screen if user presses register
     */
    private void goToRegistration() {
        final Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
