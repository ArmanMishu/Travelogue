package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeasonalSpecial extends AppCompatActivity {

    Button bogalake_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasonal_special);

        bogalake_btn = findViewById(R.id.bogalake);

    }
}