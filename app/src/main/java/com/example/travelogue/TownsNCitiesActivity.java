package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TownsNCitiesActivity extends AppCompatActivity {

    Button dhaka_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_towns_ncities);

        dhaka_btn = findViewById(R.id.dhaka);


        dhaka_btn.setOnClickListener(new View.OnClickListener() {

            String s1 = "dhaka";

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TownsNCitiesActivity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });
    }
}