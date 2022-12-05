package com.example.streetfighterguide;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);

    }

    public void login (View view) {
        MainActivity.gotoActivity(this, LoginActivity.class);
    }

    public void register (View view) {
        MainActivity.gotoActivity(this, RegisterActivity.class);
    }

}