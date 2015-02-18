package com.example.kuldeep.parselogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginSignupActivity extends Activity {

    // Declare Variables
    Button loginbutton;
    Button signup;
    String usernametxt;
    String passwordtxt;
    EditText password;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        // Locate EditTexts in main.xml
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        // Locate Buttons in main.xml
        loginbutton = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();

                // Send data to Parse.com for verification
                ParseUser.logInInBackground(usernametxt,passwordtxt,new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            // If user exist and authenticated, send user to Welcome.class
                            Intent intent = new Intent(LoginSignupActivity.this,Welcome.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Successfully Logged in",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No such user exists! Please signup!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        // Sign up Button Click Listener
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();

                //force the user to fillup the form
                if(username.equals("") && password.equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();
                }

                //else save the user into parse.com data storage
                else{
                    ParseUser user = new ParseUser();
                    user.setUsername(usernametxt);
                    user.setPassword(passwordtxt);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null){
                                //show acceptance toast
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Signed up, please log in.",
                                        Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),
                                        "Sign up Error", Toast.LENGTH_LONG)
                                        .show();
                            }

                        }
                    });
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_signup, menu);
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
}
