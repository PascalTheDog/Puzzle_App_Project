package com.example.david.puzzle_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    final private int SPLASH_TIMER = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Intent mainActivity = new Intent(this, MainActivity.class);
        Thread timer = new Thread() {
          public void run() {
              try {
                  sleep(SPLASH_TIMER);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } finally {
                  startActivity(mainActivity);
                  finish();
              }
            }
        };

        timer.start();
    }
}
