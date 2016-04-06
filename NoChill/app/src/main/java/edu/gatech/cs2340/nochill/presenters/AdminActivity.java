package edu.gatech.cs2340.nochill.presenters;

import android.app.AlertDialog;
//import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.gatech.cs2340.nochill.R;
import edu.gatech.cs2340.nochill.models.CurrentUser;
import edu.gatech.cs2340.nochill.models.Users;

public class AdminActivity extends ActionBarActivity {

    /**
     * Spinner object created to unban users
     */
    private Spinner unbanSpinner;
    /**
     * Array storing users to unban
     */
    private ArrayAdapter<String> unbanAdapt; //Adapter displaying banned users

    /**
     * Spinner to ban user
     */
    private Spinner banSpinner;
    /**
     * Array used to store users to ban
     */
    private ArrayAdapter<String> banAdapt; //Adapter displaying unbanned users

    /**
     * Spinner to make a nonadmin
     */
    private Spinner nonAdminSpinner;
    //private ArrayAdapter<String> nonAdminAdapt; //Adapter displaying unbanned users

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setTitle("Admin Page");

        banSpinner = (Spinner) findViewById(R.id.banSpinner);
        banAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Users.getUnbannedUsers());
        banSpinner.setAdapter(banAdapt);


        unbanSpinner = (Spinner) findViewById(R.id.unbanSpinner);
        unbanAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Users.getBannedUsers());
        unbanSpinner.setAdapter(unbanAdapt);

        nonAdminSpinner = (Spinner) findViewById(R.id.addAdminSpinner);
        nonAdminSpinner.setAdapter(banAdapt);

        final Button banButt = ((Button) findViewById(R.id.banButton));


        banButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = (String) banSpinner.getSelectedItem();
                attemptBan(username);
            }

        });


        final Button unbanButt = ((Button) findViewById(R.id.unBanButton));
        unbanButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = (String) unbanSpinner.getSelectedItem();
                attemptUnban(username);
            }

        });

        final Button addAdminButt = ((Button) findViewById(R.id.addAdminButton));
        if(!CurrentUser.getProfile().isSuperAdmin()){
            addAdminButt.setEnabled(false);
        } else {
            addAdminButt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String username = (String) nonAdminSpinner.getSelectedItem();
                    attemptAdmin(username);
                }

            });
        }

    }

    /**
     * attempts to make a user an admin
     * @param username admin username to add
     */
    private void attemptAdmin(final String username){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to make " + username + " an admin?")
                .setCancelable(false)
                .setPositiveButton("Yeah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Ban user
                        Users.makeAdmin(username);
                        updateAdapters();
                    }
                })
                .setNegativeButton("Nah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Bans user
     * @param username of user to ban
     */
    private void attemptBan(final String username){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to ban " + username)
                .setCancelable(false)
                .setPositiveButton("Yeah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Ban user
                        Users.banUser(username);
                        updateAdapters();
                    }
                })
                .setNegativeButton("Nah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Unbans user
     * @param username of user to unban
     */
    private void attemptUnban(final String username){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to unban " + username)
                .setCancelable(false)
                .setPositiveButton("Yeah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Ban user
                        Users.unbanUser(username);
                        updateAdapters();
                    }
                })
                .setNegativeButton("Nah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Updates adapters
     */
    private void updateAdapters(){
        banAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Users.getUnbannedUsers());
        banSpinner.setAdapter(banAdapt);

        unbanAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Users.getBannedUsers());
        unbanSpinner.setAdapter(unbanAdapt);

        //Can only admin unbanned users
        nonAdminSpinner.setAdapter(banAdapt);
    }

}
