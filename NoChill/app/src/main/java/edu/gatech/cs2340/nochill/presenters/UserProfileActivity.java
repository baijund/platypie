package edu.gatech.cs2340.nochill.presenters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import edu.gatech.cs2340.nochill.models.CurrentUser;
import edu.gatech.cs2340.nochill.models.Profile;
import edu.gatech.cs2340.nochill.R;

public class UserProfileActivity extends ActionBarActivity {

    private final Context thisContext = this;

    private Profile p = CurrentUser.getProfile();
    private EditText fname;
    private EditText lname;
    private EditText email;
    private EditText aboutMe;
    private Spinner major;

    /**
     * Creates user profile screen
     * @param savedInstanceState creates screen
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
        major = (Spinner) findViewById(R.id.majorLine2);

        final ArrayAdapter adapt = (ArrayAdapter) major.getAdapter();
        final int position = adapt.getPosition(p.getMajor());
        major.setSelection(position);




        fname.setText(p.getFirstName(), TextView.BufferType.EDITABLE);
        lname.setText(p.getLastName(), TextView.BufferType.EDITABLE);
        email.setText(p.getEmail(), TextView.BufferType.EDITABLE);
        aboutMe.setText(p.getAboutMe(), TextView.BufferType.EDITABLE);


        final Button updateButton = (Button) findViewById(R.id.updateButton);
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
        p.setMajor(major.getSelectedItem().toString());


        final Response.Listener rl = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("editUser response: ", response);

                boolean added = false;
                try {
                    final JSONObject res = new JSONObject(response);
                    added = !res.getBoolean("error");
                } catch (Exception e){
                    Log.i("Error: ", e.toString());
                }


                if (added){
                    Toast.makeText(thisContext, "Profile Edited!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(thisContext, "Profile NOT Edited!",
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

        CurrentUser.editProfile(p, rl, el);

    }

}
