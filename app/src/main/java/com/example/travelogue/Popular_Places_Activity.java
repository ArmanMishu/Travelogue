package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Popular_Places_Activity extends AppCompatActivity {

    Button coxBazar,sylhet,sreemangal,rangamati,kewkradong,sajek,sitakundu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_places);


        coxBazar = findViewById(R.id.coxs_bazar);
        sylhet=findViewById(R.id.sylhet);
        sreemangal=findViewById(R.id.sreemangal);
        rangamati=findViewById(R.id.rangamati);
        kewkradong=findViewById(R.id.kewkradong);
        sajek=findViewById(R.id.sajek);
        sitakundu=findViewById(R.id.sitakundu);

        coxBazar.setOnClickListener(new View.OnClickListener() {

            String s1 = "Cox's Bazar";

            @Override
            public void onClick(View view) {

                Intent i = new Intent(Popular_Places_Activity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        sylhet.setOnClickListener(new View.OnClickListener() {

            String s1 = "Sylhet";
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Popular_Places_Activity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        sreemangal.setOnClickListener(new View.OnClickListener() {

            String s1= "Sreemangal";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(Popular_Places_Activity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        rangamati.setOnClickListener(new View.OnClickListener() {

            String s1= "Rangamati";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(Popular_Places_Activity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        kewkradong.setOnClickListener(new View.OnClickListener() {

            String s1= "Kewkradong";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(Popular_Places_Activity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        sajek.setOnClickListener(new View.OnClickListener() {

            String s1= "Sajek";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(Popular_Places_Activity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

        sitakundu.setOnClickListener(new View.OnClickListener() {

            String s1= "Sitakundu";

            @Override
            public void onClick(View view) {

                Intent i=new Intent(Popular_Places_Activity.this, SiteActivity.class);
                i.putExtra("site", s1);
                startActivity(i);
            }
        });

    }
}