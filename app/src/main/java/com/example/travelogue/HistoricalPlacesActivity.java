package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistoricalPlacesActivity extends AppCompatActivity {

    Button ahsan_manjil,the_american_church,lalbagh,leberation_war_museum,somppura_mahavira,saheed_minar,shat_gombuj,panam_city,mohasthanghar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_places);

        ahsan_manjil = findViewById(R.id.ahsan_manjil);
        the_american_church = findViewById(R.id.the_american_church);
        lalbagh = findViewById(R.id.lalbagh);
        leberation_war_museum = findViewById(R.id.leberation_war_museum);
        somppura_mahavira = findViewById(R.id.somppura_mahavira);
        saheed_minar = findViewById(R.id.saheed_minar);
        shat_gombuj = findViewById(R.id.shat_gombuj);
        panam_city = findViewById(R.id.panam_city);
        mohasthanghar = findViewById(R.id.mohasthanghar);



        ahsan_manjil.setOnClickListener(new View.OnClickListener() {

            String s1 = "Ahsan Manjil";

            @Override
            public void onClick(View view) {

                Intent i = new Intent(HistoricalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        the_american_church.setOnClickListener(new View.OnClickListener() {

            String s1 = "The American Church";
            @Override
            public void onClick(View view) {

                Intent i=new Intent(HistoricalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        lalbagh.setOnClickListener(new View.OnClickListener() {

            String s1= "Lal Bagh Fort";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(HistoricalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        leberation_war_museum.setOnClickListener(new View.OnClickListener() {

            String s1= "The Liberation War Museum";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(HistoricalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        somppura_mahavira.setOnClickListener(new View.OnClickListener() {

            String s1= "Sompura Mahavira";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(HistoricalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        saheed_minar.setOnClickListener(new View.OnClickListener() {

            String s1= "Saheed Minar";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(HistoricalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        shat_gombuj.setOnClickListener(new View.OnClickListener() {

            String s1= "Shat Gambuj Mosque";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(HistoricalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        panam_city.setOnClickListener(new View.OnClickListener() {

            String s1= "Panam City";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(HistoricalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        mohasthanghar.setOnClickListener(new View.OnClickListener() {

            String s1= "Mohasthangarh";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(HistoricalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });


    }
}