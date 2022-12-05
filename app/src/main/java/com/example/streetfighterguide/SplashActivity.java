package com.example.streetfighterguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private final Timer timer = new Timer();
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotoHall();
                    }
                });
            }
        };
        timer.schedule(timerTask, 3000);
    }

    private void gotoHall() {
        Intent intent = new Intent(SplashActivity.this, HallActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}