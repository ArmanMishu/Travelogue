package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button logout,popular_places,historical_places_button, seasonal_button, cultural_button,all_places_button,towns_cities_button,museums_button,search_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logout_button);
        popular_places=findViewById(R.id.popular_places_button);
        historical_places_button=findViewById(R.id.historical_places);
        seasonal_button = findViewById(R.id.seasonal_special);
        cultural_button = findViewById(R.id.cultural_places);
        towns_cities_button = findViewById(R.id.towns_cities);
        museums_button =findViewById(R.id.museums);
        all_places_button = findViewById(R.id.all_places);
        search_btn=findViewById(R.id.search_button);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,AllPlacesActivity.class);
                startActivity(intent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        popular_places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Popular_Places_Activity.class);
                startActivity(i);
            }
        });

        seasonal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SeasonalSpecial.class);
                startActivity(intent);
            }
        });


        historical_places_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, HistoricalPlacesActivity.class);
                startActivity(intent);
            }
        });

        cultural_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CulturalPlacesActivity.class);
                startActivity(intent);
            }
        });
        towns_cities_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TownsNCitiesActivity.class);
                startActivity(intent);
            }
        });

        museums_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MuseumsActivity.class);
                startActivity(intent);
            }
        });


        all_places_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllPlacesActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}