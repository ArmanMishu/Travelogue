package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeasonalSpecial extends AppCompatActivity {

    Button bogalake,kewkradong,saint_martin,marayontong,sajek,sundarban,nilgiri,coxsbazar,chandranath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasonal_special);

        bogalake = findViewById(R.id.bogalake);
        kewkradong = findViewById(R.id.kewkradong);
        saint_martin = findViewById(R.id.saint_martin);
        marayontong = findViewById(R.id.marayontong);
        sajek = findViewById(R.id.sajek);
        sundarban = findViewById(R.id.sundarban);
        nilgiri = findViewById(R.id.nilgiri);
        coxsbazar = findViewById(R.id.coxsbazar);
        chandranath = findViewById(R.id.chandranath);

        bogalake.setOnClickListener(new View.OnClickListener() {

            String s1= "Bogalake";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeasonalSpecial.this,SiteActivity.class);
                i.putExtra("site",s1);
                startActivity(i);

            }
        });
        kewkradong.setOnClickListener(new View.OnClickListener() {

            String s1= "Kewkradong";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeasonalSpecial.this,SiteActivity.class);
                i.putExtra("site",s1);
                startActivity(i);

            }
        });
        saint_martin.setOnClickListener(new View.OnClickListener() {

            String s1= "Saint Martin";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeasonalSpecial.this,SiteActivity.class);
                i.putExtra("site",s1);
                startActivity(i);

            }
        });
        marayontong.setOnClickListener(new View.OnClickListener() {

            String s1= "Marayontong";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeasonalSpecial.this,SiteActivity.class);
                i.putExtra("site",s1);
                startActivity(i);

            }
        });
        sajek.setOnClickListener(new View.OnClickListener() {

            String s1= "Sajek";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeasonalSpecial.this,SiteActivity.class);
                i.putExtra("site",s1);
                startActivity(i);

            }
        });
        sundarban.setOnClickListener(new View.OnClickListener() {

            String s1= "Sundarban";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeasonalSpecial.this,SiteActivity.class);
                i.putExtra("site",s1);
                startActivity(i);

            }
        });
        nilgiri.setOnClickListener(new View.OnClickListener() {

            String s1= "Nilgiri";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeasonalSpecial.this,SiteActivity.class);
                i.putExtra("site",s1);
                startActivity(i);

            }
        });
        coxsbazar.setOnClickListener(new View.OnClickListener() {

            String s1= "Cox's Bazar";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeasonalSpecial.this,SiteActivity.class);
                i.putExtra("site",s1);
                startActivity(i);

            }
        });
        chandranath.setOnClickListener(new View.OnClickListener() {

            String s1= "Chandranath";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeasonalSpecial.this,SiteActivity.class);
                i.putExtra("site",s1);
                startActivity(i);

            }
        });


    }
}