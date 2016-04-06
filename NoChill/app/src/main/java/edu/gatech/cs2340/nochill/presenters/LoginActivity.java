package edu.gatech.cs2340.nochill.presenters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import edu.gatech.cs2340.nochill.models.CurrentUser;
import edu.gatech.cs2340.nochill.models.Profile;
import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.Users;


public class LoginActivity extends ActionBarActivity {

    private final Context thisContext = this;

    /**
     * Creates screen
     * @param savedInstanceState screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkUserAndPass();
            }
        });

    }

    /**
     * Goes to main screen if user presses cancel
     */
    private void goToMainActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Checks if password matches confirm password and stores profile in an arraylist
     */
    private void checkUserAndPass(){
        final String username = ((EditText) findViewById(R.id.userName)).getText().toString();
        final String password = ((EditText) findViewById(R.id.password)).getText().toString();
        final Response.Listener<String> rl = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Profile p = null;
                try{
                    final JSONObject res = new JSONObject(response);
                    final String firstname = res.getString("firstname");
                    final String lastname = res.getString("lastname");
                    final String email = res.getString("email");
                    final String username = res.getString("username");
                    final String aboutme = res.getString("aboutme");
                    final String major = res.getString("major");
                    p = new Profile(firstname, lastname, email, username, password, major);
                    p.setAboutMe(aboutme);
                } catch (JSONException e){
                    Log.i("error: ", e.toString());
                }

                if(p != null && p.getPassword().equals(password) && !p.isBanned()){
                    Toast.makeText(thisContext, "Congratulations you entered the APP!!!!",
                            Toast.LENGTH_LONG).show();
                    CurrentUser.login(p);
                    CurrentUser.getProfile().setPassword(password);
                    goToLoggedIn();
                } else if(p != null && p.isBanned()) {
                    Toast.makeText(thisContext, "Bad user, you are banned.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(thisContext, "Incorrect login",
                            Toast.LENGTH_LONG).show();
                }
            }
        };

        final Response.ErrorListener el = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("getUser error: ", error.toString());
            }
        };

        Users.login(username, password, rl, el);

    }

    /**
     * Creates options menu
     * @param menu initiates menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    /**
     * Creates option menu
     * @param item selects item
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
     * Goes to login screen after account has been made
     */
    private void goToLoggedIn() {
        final Intent intent = new Intent(this, LoggedInActivity.class);
        startActivity(intent);
    }
}
