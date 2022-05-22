package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MuseumsActivity extends AppCompatActivity {

    Button nationalmeuseum,novotheatre,bangabandhu_memorial,liberation_war,bangladesh_military,varendra_research,independence_museum,sonargaon_folk,shilpacharya_zainul_abedin_sangrahashala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museums);

        nationalmeuseum = findViewById(R.id.national_museum);
        novotheatre = findViewById(R.id.novotheatre);
        bangabandhu_memorial = findViewById(R.id.bangabandhu_memorial);
        liberation_war = findViewById(R.id.liberation_war);
        bangladesh_military = findViewById(R.id.bangladesh_military);
        varendra_research = findViewById(R.id.varendra_research);
        independence_museum = findViewById(R.id.independence_museum);
        sonargaon_folk = findViewById(R.id.sonargaon_folk);
        shilpacharya_zainul_abedin_sangrahashala = findViewById(R.id.shilpacharya_zainul_abedin_sangrahashala);


        nationalmeuseum.setOnClickListener(new View.OnClickListener() {

            String s1 = "National Museum";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MuseumsActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        novotheatre.setOnClickListener(new View.OnClickListener() {

            String s1 = "Novo Theatre";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MuseumsActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        bangabandhu_memorial.setOnClickListener(new View.OnClickListener() {

            String s1 = "Bangabandhu Memorial";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MuseumsActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        liberation_war.setOnClickListener(new View.OnClickListener() {

            String s1 = "The Liberation War Museum";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MuseumsActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        bangladesh_military.setOnClickListener(new View.OnClickListener() {

            String s1 = "Bangladesh Military";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MuseumsActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        varendra_research.setOnClickListener(new View.OnClickListener() {

            String s1 = "Varendra Research";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MuseumsActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        independence_museum.setOnClickListener(new View.OnClickListener() {

            String s1 = "Independence";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MuseumsActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        sonargaon_folk.setOnClickListener(new View.OnClickListener() {

            String s1 = "Sonargaon";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MuseumsActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
        shilpacharya_zainul_abedin_sangrahashala.setOnClickListener(new View.OnClickListener() {

            String s1 = "Shilpacharya";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MuseumsActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
    }
}