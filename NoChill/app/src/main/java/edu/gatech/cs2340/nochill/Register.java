package edu.gatech.cs2340.nochill;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import java.util.List;


public class Register extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkPassAndConfirm();
            }
        });
    }

    private void checkPassAndConfirm(){
        String password = ((EditText) findViewById(R.id.passwordLine)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.confirmPasswordLine)).getText().toString();
        if(password.equals(confirmPassword)) {
            Toast.makeText(this, "Congratulations, you have registered!!!!",
                    Toast.LENGTH_LONG).show();


            String firstName = ((EditText) findViewById(R.id.firstNameLine)).getText().toString();
            String lastName = ((EditText) findViewById(R.id.lastNameLine)).getText().toString();
            String email = ((EditText) findViewById(R.id.emailLine)).getText().toString();
            String username = ((EditText) findViewById(R.id.usernameLine)).getText().toString();


            Users.addUser(new Profile(firstName, lastName, email, username, password));



            goToLogin();
        } else {
            Toast.makeText(this, "Password and Confirm password do not match",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}