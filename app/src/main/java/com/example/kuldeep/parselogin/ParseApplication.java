package com.example.kuldeep.parselogin;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by Kuldeep on 2/19/2015.
 */

//kuldeep chanaged the project again
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "MrkxcjSNkLte4MQQycE83O1Y10hHQ40bTI8L18Pv", "hr6qdFS8nJ74fZTr0GodzcSAdVw6bcDQSZLxbOhv");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
