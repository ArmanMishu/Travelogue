package com.example.travelogue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SiteActivity extends AppCompatActivity {

    TextView site_name, details_text,db_todo,db_not_todo;
    Button todo_btn,hotel_booking,emergency_btn,cd_close_btn;
    ImageSlider image_slider;
    DatabaseReference dbReference;
    FirebaseDatabase dbfirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);

        site_name = findViewById(R.id.demo_site_name);
        image_slider = findViewById(R.id.demo_image_slider);
        details_text = findViewById(R.id.demotext);
        todo_btn = findViewById(R.id.site_activity_todo);

        Intent intent = getIntent();
        String site = intent.getStringExtra("site");

        if (site.equals("Cox's Bazar")) {
            site_name.setText("Cox's Bazar");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("coxs_bazar")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot images : dataSnapshot.getChildren()) {
                                siteImages.add(new SlideModel(images.child("url").getValue(String.class), images.child("title").getValue(String.class), ScaleTypes.FIT));
                            }

                            image_slider.setImageList(siteImages, ScaleTypes.FIT);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


            dbfirebase = FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/");
            dbReference = dbfirebase.getReference("Travelogue");

            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    String getSiteDetails = dataSnapshot.child("Coxs_bazar").child("site_details").getValue(String.class);


                    details_text.setText(getSiteDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        if (site.equals("Sylhet")) {
            site_name.setText("Sylhet");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("sylhet")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot images : dataSnapshot.getChildren()) {
                                siteImages.add(new SlideModel(images.child("url").getValue(String.class), images.child("title").getValue(String.class), ScaleTypes.FIT));
                            }

                            image_slider.setImageList(siteImages, ScaleTypes.FIT);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


            dbfirebase = FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/");
            dbReference = dbfirebase.getReference("Travelogue");

            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    String getSiteDetails = dataSnapshot.child("sylhet").child("site_details").getValue(String.class);


                    details_text.setText(getSiteDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        if (site.equals("Sreemangal")) {
            site_name.setText("Sreemangal");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("sreemangal")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot images : dataSnapshot.getChildren()) {
                                siteImages.add(new SlideModel(images.child("url").getValue(String.class), images.child("title").getValue(String.class), ScaleTypes.FIT));
                            }

                            image_slider.setImageList(siteImages, ScaleTypes.FIT);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


            dbfirebase = FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/");
            dbReference = dbfirebase.getReference("Travelogue");

            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    String getSiteDetails = dataSnapshot.child("sreemangal").child("site_details").getValue(String.class);


                    details_text.setText(getSiteDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        if (site.equals("Rangamati")) {
            site_name.setText("Rangamati");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("rangamati")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot images : dataSnapshot.getChildren()) {
                                siteImages.add(new SlideModel(images.child("url").getValue(String.class), images.child("title").getValue(String.class), ScaleTypes.FIT));
                            }

                            image_slider.setImageList(siteImages, ScaleTypes.FIT);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


            dbfirebase = FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/");
            dbReference = dbfirebase.getReference("Travelogue");

            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    String getSiteDetails = dataSnapshot.child("rangamati").child("site_details").getValue(String.class);


                    details_text.setText(getSiteDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        if (site.equals("Kewkradong")) {
            site_name.setText("Kewkradong");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("kewkradong")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot images : dataSnapshot.getChildren()) {
                                siteImages.add(new SlideModel(images.child("url").getValue(String.class), images.child("title").getValue(String.class), ScaleTypes.FIT));
                            }

                            image_slider.setImageList(siteImages, ScaleTypes.FIT);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


            dbfirebase = FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/");
            dbReference = dbfirebase.getReference("Travelogue");

            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    String getSiteDetails = dataSnapshot.child("kewkradong").child("site_details").getValue(String.class);


                    details_text.setText(getSiteDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        if (site.equals("Sajek")) {
            site_name.setText("Sajek");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("sajek")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot images : dataSnapshot.getChildren()) {
                                siteImages.add(new SlideModel(images.child("url").getValue(String.class), images.child("title").getValue(String.class), ScaleTypes.FIT));
                            }

                            image_slider.setImageList(siteImages, ScaleTypes.FIT);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


            dbfirebase = FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/");
            dbReference = dbfirebase.getReference("Travelogue");

            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    String getSiteDetails = dataSnapshot.child("sajek").child("site_details").getValue(String.class);


                    details_text.setText(getSiteDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        if (site.equals("Sitakundu")) {
            site_name.setText("Sitakundu");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("sitakundu")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot images : dataSnapshot.getChildren()) {
                                siteImages.add(new SlideModel(images.child("url").getValue(String.class), images.child("title").getValue(String.class), ScaleTypes.FIT));
                            }

                            image_slider.setImageList(siteImages, ScaleTypes.FIT);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


            dbfirebase = FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/");
            dbReference = dbfirebase.getReference("Travelogue");

            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    String getSiteDetails = dataSnapshot.child("Sitakundu").child("site_details").getValue(String.class);


                    details_text.setText(getSiteDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openTodoDialogue();

            }
        });

    }

    private void openTodoDialogue() {

        Dialog dialog=new Dialog(SiteActivity.this, R.style.CustomDialogueTheme);
        LayoutInflater layoutInflater= this.getLayoutInflater();
        View todo_cd= layoutInflater.inflate(R.layout.todo_custom_dialogue,null);

        db_todo=todo_cd.findViewById(R.id.cd_db_todo);
        db_not_todo=todo_cd.findViewById(R.id.cd_db_Nottodo);
        cd_close_btn=todo_cd.findViewById(R.id.cd_close_btn);


        cd_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }
}