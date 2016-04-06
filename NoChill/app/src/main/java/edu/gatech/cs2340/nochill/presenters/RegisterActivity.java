package edu.gatech.cs2340.nochill.presenters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import edu.gatech.cs2340.nochill.models.Profile;
import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.Users;


public class RegisterActivity extends ActionBarActivity {

    /**
     * Screen where RegisterActivity is created
     */
    private final Context thisContext = this;

    /**
     * Creates registration screen
     * @param savedInstanceState creates screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Register");

        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        final Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkPassAndConfirm();
            }
        });
    }

    /**
     * checks the credentials of the user/password and username combination
     */
    private void checkPassAndConfirm(){
        final String password = ((EditText) findViewById(R.id.passwordLine)).getText().toString();
        final String confirmPassword = ((EditText) findViewById(R.id.confirmPasswordLine)).getText().toString();
        if(password.equals(confirmPassword)) {

            final String firstName = ((EditText) findViewById(R.id.firstNameLine)).getText().toString();
            final String lastName = ((EditText) findViewById(R.id.lastNameLine)).getText().toString();
            final String email = ((EditText) findViewById(R.id.emailLine)).getText().toString();
            final String username = ((EditText) findViewById(R.id.usernameLine)).getText().toString();
            final String major = ((Spinner) findViewById(R.id.majorLine)).getSelectedItem().toString();


            final Response.Listener rl = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("getUser response: ", response);

                    boolean added = false;
                    try {
                        final JSONObject res = new JSONObject(response);
                        added = !res.getBoolean("error");
                    } catch (JSONException e){
                        Log.i("Error: ", e.toString());
                    }


                    if (!added){
                        Toast.makeText(thisContext, "User not added",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(thisContext, "Congratulations, you have registered!!!!",
                                Toast.LENGTH_LONG).show();
                        goToLogin();
                    }
                }
            };

            final Response.ErrorListener el = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("getUser error: ", error.toString());
                }
            };

            Users.addUser(new Profile(firstName, lastName, email, username, password, major), rl, el);



        } else {
            Toast.makeText(this, "Password and Confirm password do not match",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * goes to login screen
     */
    private void goToLogin() {
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Creates the menu
     * @param menu initiation
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);

        return true;
    }

    /**
     * Acts when item is selected
     * @param item selected
     * @return item selected
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
     * Goes to main activity
     */
    private void goToMainActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
