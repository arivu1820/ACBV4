package com.futureinspirator.acbaradise;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private Handler delay,handler,toverificationactivity;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageview);
        txt = findViewById(R.id.txt);
        delay = new Handler();
        handler = new Handler();
        toverificationactivity = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run()
            {
                ObjectAnimator animationY = ObjectAnimator.ofFloat(image,"Y",60f);
                animationY.setDuration(800);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animationY);
                animatorSet.start();

                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        txt.setVisibility(View.VISIBLE);
                    }
                };
                handler.postDelayed(run,1000);
            }
        };

        delay.postDelayed(runnable,500);

        Runnable toverificationpage = new Runnable() {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish();
            }
        };
        toverificationactivity.postDelayed(toverificationpage,3000);
    }
}