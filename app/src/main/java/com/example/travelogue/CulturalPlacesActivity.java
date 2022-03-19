package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class CulturalPlacesActivity extends AppCompatActivity {

   Button baitul_mukkarram_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultural_places);

        baitul_mukkarram_btn = findViewById(R.id.baytul_mukkaram);
    }
}