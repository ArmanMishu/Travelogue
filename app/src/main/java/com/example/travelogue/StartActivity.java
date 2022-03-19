package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    Button login_page_connector, register_page_connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        login_page_connector = findViewById(R.id.login_button);
        register_page_connector = findViewById(R.id.create_account_button);

        login_page_connector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        register_page_connector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}