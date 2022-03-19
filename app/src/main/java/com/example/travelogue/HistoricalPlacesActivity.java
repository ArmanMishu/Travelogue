package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class HistoricalPlacesActivity extends AppCompatActivity {

    Button american_church;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_places);

        american_church = findViewById(R.id.the_american_church);
    }
}