package com.example.ordermanagement;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ordermanagement.HomeActivity.MVP.HomeActivity;
import com.example.ordermanagement.Login.MVP.LogInActivity;
import com.example.ordermanagement.Utilities.SharedPref;

public class SplashActivity extends AppCompatActivity {

    android.os.Handler Handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        SharedPref sharedPref=new SharedPref(this);

        if(sharedPref.getAccessToken()=="")
        {
            Handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    Intent intent=new Intent(SplashActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            },2000);
        }
        else
        {
            Handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            },2000);
        }
    }
}
