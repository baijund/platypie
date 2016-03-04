package edu.gatech.cs2340.nochill.presenters;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.nochill.models.CurrentUser;
import edu.gatech.cs2340.nochill.models.Profile;
import edu.gatech.cs2340.nochill.R;

public class UserProfileActivity extends ActionBarActivity {

    Profile p = CurrentUser.getProfile();
    EditText fname;
    EditText lname;
    EditText email;
    EditText aboutMe;
    EditText major;

    /**
     * Creates user profile screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        setTitle("Profile");

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        aboutMe = (EditText) findViewById(R.id.aboutme);
        major = (EditText) findViewById(R.id.major);


        fname.setText(p.getFirstName(), TextView.BufferType.EDITABLE);
        lname.setText(p.getLastName(), TextView.BufferType.EDITABLE);
        email.setText(p.getEmail(), TextView.BufferType.EDITABLE);
        aboutMe.setText(p.getAboutMe(), TextView.BufferType.EDITABLE);
        major.setText(p.getMajor(), TextView.BufferType.EDITABLE);

        Button updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    /**
     * Updates the user's profile
     */
    private void updateProfile(){
        p.setAboutMe(aboutMe.getText().toString());
        p.setEmail(email.getText().toString());
        p.setLastName(lname.getText().toString());
        p.setFirstName(fname.getText().toString());
        p.setMajor(major.getText().toString());
        CurrentUser.editProfile(p);
        Toast.makeText(this, "Profile Edited!",
                Toast.LENGTH_LONG).show();
    }

}
