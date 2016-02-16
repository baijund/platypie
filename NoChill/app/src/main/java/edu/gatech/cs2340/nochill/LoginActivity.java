package edu.gatech.cs2340.nochill;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.*;
import android.widget.*;
import android.widget.EditText;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        Button loginButton = (Button) findViewById(R.id.loginButton);
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Checks if password matches confirm password and stores profile in an arraylist
     */
    private void checkUserAndPass(){
        String username = ((EditText) findViewById(R.id.userName)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();

        Profile p = Users.getUser(username);
        if(p != null && p.getPassword().equals(password)){
            Toast.makeText(this, "Congratulations you entered the APP!!!!",
                    Toast.LENGTH_LONG).show();
            CurrentUser.login(p);
            goToLoggedIn();
        } else {
            Toast.makeText(this, "Incorrect credentials",
                    Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
     * Goes to login screen after account has been made
     */
    private void goToLoggedIn() {
        Intent intent = new Intent(this, LoggedInActivity.class);
        startActivity(intent);
    }
}
