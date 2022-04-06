package com.futureinspirator.acbaradise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class amcpage extends AppCompatActivity {

    private CardView splitaccardview,windowaccardview,cassetteaccardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amcpage);

        splitaccardview = findViewById(R.id.splitaccardview);
        windowaccardview = findViewById(R.id.windowaccardview);
        cassetteaccardview = findViewById(R.id.cassetteaccardview);

        splitaccardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(amcpage.this,amcsplitacschemes.class);
                startActivity(intent);
            }
        });

        windowaccardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(amcpage.this,amcwindowacschemes.class);
                startActivity(intent);
            }
        });

        cassetteaccardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(amcpage.this,amccassetteacschemes.class);
                startActivity(intent);
            }
        });
    }
}