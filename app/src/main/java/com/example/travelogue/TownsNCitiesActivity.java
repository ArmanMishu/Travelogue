package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TownsNCitiesActivity extends AppCompatActivity {

    Button dhaka,sylhet,rajshahi,chattogram,rangamati,bagherhat,gazipur,panchgarh,tangail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_towns_ncities);

        dhaka = findViewById(R.id.dhaka);
        sylhet = findViewById(R.id.sylhet);
        rajshahi = findViewById(R.id.rajshahi);
        chattogram = findViewById(R.id.chattogram);
        rangamati = findViewById(R.id.rangamati);
        bagherhat = findViewById(R.id.bagherhat);
        gazipur = findViewById(R.id.gazipur);
        panchgarh = findViewById(R.id.panchgarh);
        tangail = findViewById(R.id.tangail);


        dhaka.setOnClickListener(new View.OnClickListener() {

            String s1 = "Dhaka";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TownsNCitiesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        sylhet.setOnClickListener(new View.OnClickListener() {

            String s1 = "Sylhet";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TownsNCitiesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        rajshahi.setOnClickListener(new View.OnClickListener() {

            String s1 = "Rajshahi";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TownsNCitiesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        chattogram.setOnClickListener(new View.OnClickListener() {

            String s1 = "Chattogram";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TownsNCitiesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        rangamati.setOnClickListener(new View.OnClickListener() {

            String s1 = "Rangamati";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TownsNCitiesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        bagherhat.setOnClickListener(new View.OnClickListener() {

            String s1 = "Bagerhat";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TownsNCitiesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        gazipur.setOnClickListener(new View.OnClickListener() {

            String s1 = "Gazipur";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TownsNCitiesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        panchgarh.setOnClickListener(new View.OnClickListener() {

            String s1 = "Panchagarh";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TownsNCitiesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        tangail.setOnClickListener(new View.OnClickListener() {

            String s1 = "Tangail";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TownsNCitiesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
    }
}