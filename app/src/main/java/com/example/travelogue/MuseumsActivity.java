package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MuseumsActivity extends AppCompatActivity {

    Button nationalmeuseum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museums);

        nationalmeuseum = findViewById(R.id.national_museum);

        nationalmeuseum.setOnClickListener(new View.OnClickListener() {

            String s1 = "nationalmeuseum";
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MuseumsActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
    }
}