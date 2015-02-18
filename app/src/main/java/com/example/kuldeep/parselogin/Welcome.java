package com.example.kuldeep.parselogin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;


public class Welcome extends Activity {

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //retrive the current user
        ParseUser currentUser = ParseUser.getCurrentUser();

        //convert currentuser into string
        String struser = currentUser.getUsername().toString();

        //Locate textview in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);

        // Set the currentUser String into TextView
        txtuser.setText("You are logged in as " + struser);

        // Locate Button in welcome.xml
        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                finish();
            }
        });
    }

}
