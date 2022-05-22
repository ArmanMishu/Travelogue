package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CulturalPlacesActivity extends AppCompatActivity {

    Button baytul_mukkaram,dhakeshwari,national_memorial,rabindranath_kuthibari,kotila_mura,old_dhaka,atiya_mosque,chandranath_temple,shalbon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultural_places);

        baytul_mukkaram = findViewById(R.id.baytul_mukkaram);
        dhakeshwari = findViewById(R.id.dhakeshwari);
        national_memorial = findViewById(R.id.national_memorial);
        rabindranath_kuthibari = findViewById(R.id.rabindranath_kuthibari);
        kotila_mura = findViewById(R.id.kotila_mura);
        old_dhaka = findViewById(R.id.old_dhaka);
        atiya_mosque = findViewById(R.id.atiya_mosque);
        chandranath_temple = findViewById(R.id.chandranath_temple);
        shalbon = findViewById(R.id.shalbon);

        baytul_mukkaram.setOnClickListener(new View.OnClickListener() {
            String s1 = "Baitul Mukarram";


            @Override
            public void onClick(View view) {
                Intent i = new Intent(CulturalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        dhakeshwari.setOnClickListener(new View.OnClickListener() {
            String s1 = "Dhakeshwari Mandir";


            @Override
            public void onClick(View view) {
                Intent i = new Intent(CulturalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        national_memorial.setOnClickListener(new View.OnClickListener() {
            String s1 = "National Memorial";


            @Override
            public void onClick(View view) {
                Intent i = new Intent(CulturalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        rabindranath_kuthibari.setOnClickListener(new View.OnClickListener() {
            String s1 = "Rabindranath's Kuthibari";


            @Override
            public void onClick(View view) {
                Intent i = new Intent(CulturalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        kotila_mura.setOnClickListener(new View.OnClickListener() {
            String s1 = "Kotila Mura";


            @Override
            public void onClick(View view) {
                Intent i = new Intent(CulturalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        old_dhaka.setOnClickListener(new View.OnClickListener() {
            String s1 = "Old Dhaka";


            @Override
            public void onClick(View view) {
                Intent i = new Intent(CulturalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        atiya_mosque.setOnClickListener(new View.OnClickListener() {
            String s1 = "Atiya Mosque";


            @Override
            public void onClick(View view) {
                Intent i = new Intent(CulturalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        chandranath_temple.setOnClickListener(new View.OnClickListener() {
            String s1 = "Chandranath Temple";


            @Override
            public void onClick(View view) {
                Intent i = new Intent(CulturalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        shalbon.setOnClickListener(new View.OnClickListener() {
            String s1 = "Shalbon Bihar";


            @Override
            public void onClick(View view) {
                Intent i = new Intent(CulturalPlacesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

    }
}