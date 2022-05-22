package com.example.travelogue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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

    TextView site_name, details_text,todo_tv,notTodo_tv, review,cd_hotel_name, cd_hotel_contact,cd_hotel_price, cd_hotel_name2, cd_hotel_contact2,cd_hotel_price2;
    Button todo_btn,hotel_booking,emergency_btn,cd_close, submit,hotel_cd_close;
    ImageSlider image_slider;
    FirebaseDatabase dbfirebase = FirebaseDatabase.getInstance();
    DatabaseReference dbReference = dbfirebase.getReference("Travelogue");
    Dialog cd_toDo,hotel_finding;

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);

        site_name = findViewById(R.id.demo_site_name);
        image_slider = findViewById(R.id.demo_image_slider);
        details_text = findViewById(R.id.demotext);
        todo_btn = findViewById(R.id.site_activity_todo);
        emergency_btn = findViewById(R.id.demo_emergency_call_button);
        hotel_booking = findViewById(R.id.demo_site_details_hotel_booking_button);
        review=findViewById(R.id.review_text);
        ratingBar = findViewById(R.id.rating_bar);
        submit = findViewById(R.id.submit_button);




        Intent intent = getIntent();
        String site = intent.getStringExtra("site");



        cd_toDo = new Dialog(SiteActivity.this);
        cd_toDo.setContentView(R.layout.todo_custom_dialogue);
        cd_toDo.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        todo_tv = cd_toDo.findViewById(R.id.cd_db_todo);
        cd_close = cd_toDo.findViewById(R.id.cd_close_btn);
        notTodo_tv = cd_toDo.findViewById(R.id.cd_db_Nottodo);


        cd_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cd_toDo.dismiss();
            }
        });



        todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cd_toDo.show();
            }
        });

        hotel_finding = new Dialog(SiteActivity.this);
        hotel_finding.setContentView(R.layout.hotel_booking_dialogue);
        hotel_finding.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        cd_hotel_name = hotel_finding.findViewById(R.id.hotel_cd_name);
        cd_hotel_contact = hotel_finding.findViewById(R.id.hotel_cd_contact);
        cd_hotel_price = hotel_finding.findViewById(R.id.hotel_cd_price);

        cd_hotel_name2 = hotel_finding.findViewById(R.id.hotel_cd_name2);
        cd_hotel_contact2 = hotel_finding.findViewById(R.id.hotel_cd_contact2);
        cd_hotel_price2 = hotel_finding.findViewById(R.id.hotel_cd_price2);

        hotel_cd_close = hotel_finding.findViewById(R.id.hotel_cd_close);

        hotel_cd_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotel_finding.dismiss();
            }
        });



        hotel_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotel_finding.show();
            }
        });


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
                    String getTodoDetails = dataSnapshot.child("Coxs_bazar").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("Coxs_bazar").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("Coxs_bazar").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("Coxs_bazar").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("Coxs_bazar").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("Coxs_bazar").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("Coxs_bazar").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("Coxs_bazar").child("hotel_2").child("contact").getValue(String.class);

                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);

                    
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("Coxs_bazar").child("Rating").setValue("rating");

                    submit.setEnabled(false);
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
                    String getTodoDetails = dataSnapshot.child("sylhet").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("sylhet").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("sylhet").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("sylhet").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("sylhet").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("sylhet").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("sylhet").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("sylhet").child("hotel_2").child("contact").getValue(String.class);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("sylhet").child("Rating").setValue("rating");

                    submit.setEnabled(false);
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
                    String getTodoDetails = dataSnapshot.child("sreemangal").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("sreemangal").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("sreemangal").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("sreemangal").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("sreemangal").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("sreemangal").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("sreemangal").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("sreemangal").child("hotel_2").child("contact").getValue(String.class);



                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);

                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("sreemangal").child("Rating").setValue("rating");

                    submit.setEnabled(false);
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
                    String getTodoDetails = dataSnapshot.child("rangamati").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("rangamati").child("not_to_do").getValue(String.class);




                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("rangamati").child("Rating").setValue("rating");

                    submit.setEnabled(false);
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
                    String getTodoDetails = dataSnapshot.child("kewkradong").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("kewkradong").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("kewkradong").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("kewkradong").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("kewkradong").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("kewkradong").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("kewkradong").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("kewkradong").child("hotel_2").child("contact").getValue(String.class);




                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("kewkradong").child("Rating").setValue("rating");

                    submit.setEnabled(false);
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
                    String getTodoDetails = dataSnapshot.child("sajek").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("sajek").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("sajek").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("sajek").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("sajek").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("sajek").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("sajek").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("sajek").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);

                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("sajek").child("Rating").setValue("rating");

                    submit.setEnabled(false);
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


                    String getSiteDetails = dataSnapshot.child("sitakundu").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("sitakundu").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("sitakundu").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("sitakundu").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("sitakundu").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("sitakundu").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("sitakundu").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("sitakundu").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("sitakundu").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("sitakundu").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }


        if (site.equals("Dhaka")) {
            site_name.setText("Dhaka");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("dhaka")
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


                    String getSiteDetails = dataSnapshot.child("dhaka").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("dhaka").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("dhaka").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("dhaka").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("dhaka").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("dhaka").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("dhaka").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("dhaka").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("dhaka").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("dhaka").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Rajshahi")) {
            site_name.setText("Rajshahi");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("rajshahi")
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


                    String getSiteDetails = dataSnapshot.child("rajshahi").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("rajshahi").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("rajshahi").child("not_to_do").getValue(String.class);


                    String getHotelName = dataSnapshot.child("rajshahi").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("rajshahi").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("rajshahi").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("rajshahi").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("rajshahi").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("rajshahi").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);



                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("rajshahi").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Chattogram")) {
            site_name.setText("Chattogram");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("chattogram")
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


                    String getSiteDetails = dataSnapshot.child("chattogram").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("chattogram").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("chattogram").child("not_to_do").getValue(String.class);


                    String getHotelName = dataSnapshot.child("chattogram").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("chattogram").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("chattogram").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("chattogram").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("chattogram").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("chattogram").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("chattogram").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Bagerhat")) {
            site_name.setText("Bagherhat");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("bagherhat")
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


                    String getSiteDetails = dataSnapshot.child("bagherhat").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("bagherhat").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("bagherhat").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("bagherhat").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("bagherhat").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("bagherhat").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("bagherhat").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("bagherhat").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("bagherhat").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("bagherhat").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Gazipur")) {
            site_name.setText("Gazipur");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("gazipur")
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


                    String getSiteDetails = dataSnapshot.child("gazipur").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("gazipur").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("gazipur").child("not_to_do").getValue(String.class);


                    String getHotelName = dataSnapshot.child("gazipur").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("gazipur").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("gazipur").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("gazipur").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("gazipur").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("gazipur").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("gazipur").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Panchagarh")) {
            site_name.setText("Panchagarh");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("panchagarh")
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


                    String getSiteDetails = dataSnapshot.child("panchagarh").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("panchagarh").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("panchagarh").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("panchagarh").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("panchagarh").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("panchagarh").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("panchagarh").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("panchagarh").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("panchagarh").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("panchagarh").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Tangail")) {
            site_name.setText("Tangail");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("tangail")
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


                    String getSiteDetails = dataSnapshot.child("tangail").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("tangail").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("tangail").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("tangail").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("tangail").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("tangail").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("tangail").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("tangail").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("tangail").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("tangail").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }



        if (site.equals("Ahsan Manjil")) {
            site_name.setText("Ahsan Manjil");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("ahsan")
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


                    String getSiteDetails = dataSnapshot.child("ahsan").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("ahsan").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("ahsan").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("ahsan").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("ahsan").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("ahsan").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("ahsan").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("ahsan").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("ahsan").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating+"Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("ahsan").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("The American Church")) {
            site_name.setText("The American Church");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("american_church")
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


                    String getSiteDetails = dataSnapshot.child("american_church").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("american_church").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("american_church").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("american_church").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("american_church").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("american_church").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("american_church").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("american_church").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("american_church").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("american_church").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Lal Bagh Fort")) {
            site_name.setText("Lal Bagh Fort");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("lal_bagh")
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


                    String getSiteDetails = dataSnapshot.child("lal_bagh").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("lal_bagh").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("lal_bagh").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("lal_bagh").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("lal_bagh").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("lal_bagh").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("lal_bagh").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("lal_bagh").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("lal_bagh").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("lal_bagh").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("The Liberation War Museum")) {
            site_name.setText("Liberation War Museum");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("liberation_war")
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


                    String getSiteDetails = dataSnapshot.child("liberation_war").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("liberation_war").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("liberation_war").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("liberation_war").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("liberation_war").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("liberation_war").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("liberation_war").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("liberation_war").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("liberation_war").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("lal_bagh").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Sompura Mahavira")) {
            site_name.setText("Sompura Mahavira");

            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("sompura")
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


                    String getSiteDetails = dataSnapshot.child("sompura").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("sompura").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("sompura").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("sompura").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("sompura").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("sompura").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("sompura").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("sompura").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("sompura").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("sompura").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });

        }
        if (site.equals("Saheed Minar")) {
            site_name.setText("Shaheed Minar");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("shaheed_minar")
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


                    String getSiteDetails = dataSnapshot.child("shaheed_minar").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("shaheed_minar").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("shaheed_minar").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("shaheed_minar").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("shaheed_minar").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("shaheed_minar").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("shaheed_minar").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("shaheed_minar").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("shaheed_minar").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("shaheed_minar").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });



        }
        if (site.equals("Shat Gambuj Mosque")) {
            site_name.setText("Shat Gambuj Mosque");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("shat_gombuj")
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


                    String getSiteDetails = dataSnapshot.child("shat_gombuj").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("shat_gombuj").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("shat_gombuj").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("shat_gombuj").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("shat_gombuj").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("shat_gombuj").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("shat_gombuj").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("shat_gombuj").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("shat_gombuj").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("shat_gombuj").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Panam City")) {
            site_name.setText("Panam City");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("panam")
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


                    String getSiteDetails = dataSnapshot.child("panam").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("panam").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("panam").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("panam").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("panam").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("panam").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("panam").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("panam").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("panam").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("panam").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Mohasthangarh")) {
            site_name.setText("Mohasthangarh");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("mohastangarh")
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


                    String getSiteDetails = dataSnapshot.child("mohastangarh").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("mohastangarh").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("mohastangarh").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("mohastangarh").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("mohastangarh").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("mohastangarh").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("mohastangarh").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("mohastangarh").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("mohastangarh").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("mohastangarh").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }


        if (site.equals("Bogalake")) {
            site_name.setText("Bogalake");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("bogalake")
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


                    String getSiteDetails = dataSnapshot.child("bogalake").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("bogalake").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("bogalake").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("bogalake").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("bogalake").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("bogalake").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("bogalake").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("bogalake").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("bogalake").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("bogalake").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Saint Martin")) {
            site_name.setText("Saint Martin Island");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("saint_martin")
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


                    String getSiteDetails = dataSnapshot.child("saint_martin").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("saint_martin").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("saint_martin").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("saint_martin").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("saint_martin").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("saint_martin").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("saint_martin").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("saint_martin").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("saint_martin").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("saint_martin").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Marayontong")) {
            site_name.setText("Marayontong");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("marayontong")
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


                    String getSiteDetails = dataSnapshot.child("marayontong").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("marayontong").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("marayontong").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("marayontong").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("marayontong").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("marayontong").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("marayontong").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("marayontong").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("marayontong").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("marayontong").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Sundarban")) {
            site_name.setText("Sundarban");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("sundarban")
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


                    String getSiteDetails = dataSnapshot.child("sundarban").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("sundarban").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("sundarban").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("sundarban").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("sundarban").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("sundarban").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("sundarban").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("sundarban").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("sundarban").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("sundarban").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Nilgiri")) {
            site_name.setText("Nilgiri");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("nilgiri")
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


                    String getSiteDetails = dataSnapshot.child("nilgiri").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("nilgiri").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("nilgiri").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("nilgiri").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("nilgiri").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("nilgiri").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("nilgiri").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("nilgiri").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("nilgiri").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("nilgiri").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Chandranath")) {
            site_name.setText("Chandranath Hill");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("chandranath")
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


                    String getSiteDetails = dataSnapshot.child("chandranath").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("chandranath").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("chandranath").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("chandranath").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("chandranath").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("chandranath").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("chandranath").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("chandranath").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("chandranath").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("chandranath").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }



        if (site.equals("National Museum")) {
            site_name.setText("Bangladesh National Museum");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("national_museum")
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


                    String getSiteDetails = dataSnapshot.child("national_museum").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("national_museum").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("national_museum").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("national_museum").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("national_museum").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("national_museum").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("national_museum").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("national_museum").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("national_museum").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("national_museum").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Novo Theatre")) {
            site_name.setText("Novo Theatre");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("novo_theatre")
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


                    String getSiteDetails = dataSnapshot.child("novo_theatre").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("novo_theatre").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("novo_theatre").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("novo_theatre").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("novo_theatre").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("novo_theatre").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("novo_theatre").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("novo_theatre").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("novo_theatre").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("novo_theatre").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Bangabandhu Memorial")) {
            site_name.setText("Bangabandhu Memorial Museum");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("bangabandhu_memorial")
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


                    String getSiteDetails = dataSnapshot.child("bangabandhu_memorial").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("bangabandhu_memorial").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("bangabandhu_memorial").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("bangabandhu_memorial").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("bangabandhu_memorial").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("bangabandhu_memorial").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("bangabandhu_memorial").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("bangabandhu_memorial").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("bangabandhu_memorial").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("bangabandhu_memorial").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Bangladesh Military")) {
            site_name.setText("Bangladesh Military Museum");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("military_museum")
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


                    String getSiteDetails = dataSnapshot.child("military_museum").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("military_museum").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("military_museum").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("military_museum").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("military_museum").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("military_museum").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("military_museum").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("military_museum").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("military_museum").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("military_museum").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Varendra Research")) {
            site_name.setText("Varendra Research Museum");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("varendra_research")
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


                    String getSiteDetails = dataSnapshot.child("varendra_research").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("varendra_research").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("varendra_research").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("varendra_research").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("varendra_research").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("varendra_research").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("varendra_research").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("varendra_research").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("varendra_research").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("varendra_research").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Independence")) {
            site_name.setText("Independence Museum");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("independence_museum")
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


                    String getSiteDetails = dataSnapshot.child("independence_museum").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("independence_museum").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("independence_museum").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("independence_museum").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("independence_museum").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("independence_museum").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("independence_museum").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("independence_museum").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("independence_museum").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("independence_museum").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Sonargaon")) {
            site_name.setText("Sonargaon Folk Art and Craft Museum");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("sonargaon_folk")
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


                    String getSiteDetails = dataSnapshot.child("sonargaon_folk").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("sonargaon_folk").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("sonargaon_folk").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("sonargaon_folk").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("sonargaon_folk").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("sonargaon_folk").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("sonargaon_folk").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("sonargaon_folk").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("sonargaon_folk").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("sonargaon_folk").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Shilpacharya")) {
            site_name.setText("Shilpacharya Zainul Abedin Sangrahashala");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("zainul_museum")
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


                    String getSiteDetails = dataSnapshot.child("zainul_museum").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("zainul_museum").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("zainul_museum").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("zainul_museum").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("zainul_museum").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("zainul_museum").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("zainul_museum").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("zainul_museum").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("zainul_museum").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("zainul_museum").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }


        if (site.equals("Baitul Mukarram")) {
            site_name.setText("Baitul Mukkarram");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("baitul")
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


                    String getSiteDetails = dataSnapshot.child("baitul").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("baitul").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("baitul").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("baitul").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("baitul").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("baitul").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("baitul").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("baitul").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("baitul").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("baitul").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }

        if (site.equals("Dhakeshwari Mandir")) {
            site_name.setText("Dhakeshwari Mandir");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("dhakeshwari")
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


                    String getSiteDetails = dataSnapshot.child("dhakeshwari").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("dhakeshwari").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("dhakeshwari").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("dhakeshwari").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("dhakeshwari").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("dhakeshwari").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("dhakeshwari").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("dhakeshwari").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("dhakeshwari").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("dhakeshwari").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("National Memorial")) {
            site_name.setText("National Memorial");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("national_memorial")
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


                    String getSiteDetails = dataSnapshot.child("national_memorial").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("national_memorial").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("national_memorial").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("national_memorial").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("national_memorial").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("national_memorial").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("national_memorial").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("national_memorial").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("national_memorial").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("national_memorial").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Rabindranath's Kuthibari")) {
            site_name.setText("Rabindranath's Kuthibari");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("rabindra")
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


                    String getSiteDetails = dataSnapshot.child("rabindra").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("rabindra").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("rabindra").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("rabindra").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("rabindra").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("rabindra").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("rabindra").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("rabindra").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("rabindra").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("rabindra").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Kotila Mura")) {
            site_name.setText("Kotila Mura");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("kotila")
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


                    String getSiteDetails = dataSnapshot.child("kotila").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("kotila").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("kotila").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("kotila").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("kotila").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("kotila").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("kotila").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("kotila").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("kotila").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("kotila").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Old Dhaka")) {
            site_name.setText("Old Dhaka");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("old")
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


                    String getSiteDetails = dataSnapshot.child("old").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("old").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("old").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("old").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("old").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("old").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("old").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("old").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("old").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("old").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Atiya Mosque")) {
            site_name.setText("Atiya Mosque");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("atiya")
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


                    String getSiteDetails = dataSnapshot.child("atiya").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("atiya").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("atiya").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("atiya").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("atiya").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("atiya").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("atiya").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("atiya").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("atiya").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("atiya").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Chandranath Temple")) {
            site_name.setText("Chandranath Temple");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("chandranath_temple")
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


                    String getSiteDetails = dataSnapshot.child("chandranath_temple").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("chandranath_temple").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("chandranath_temple").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("chandranath_temple").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("chandranath_temple").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("chandranath_temple").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("chandranath_temple").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("chandranath_temple").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("chandranath_temple").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("chandranath_temple").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }
        if (site.equals("Shalbon Bihar")) {
            site_name.setText("Shalbon Bihar");
            final List<SlideModel> siteImages = new ArrayList<>();
            FirebaseDatabase.getInstance("https://travelogue-eb427-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Travelogue_imageSlider").child("shalbon")
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


                    String getSiteDetails = dataSnapshot.child("shalbon").child("site_details").getValue(String.class);
                    String getTodoDetails = dataSnapshot.child("shalbon").child("to_do").getValue(String.class);
                    String getNotTodoDetails = dataSnapshot.child("shalbon").child("not_to_do").getValue(String.class);

                    String getHotelName = dataSnapshot.child("shalbon").child("hotel_1").child("name").getValue(String.class);
                    String getPrice = dataSnapshot.child("shalbon").child("hotel_1").child("price").getValue(String.class);
                    String getContact = dataSnapshot.child("shalbon").child("hotel_1").child("contact").getValue(String.class);

                    String getHotelName2 = dataSnapshot.child("shalbon").child("hotel_2").child("name").getValue(String.class);
                    String getPrice2 = dataSnapshot.child("shalbon").child("hotel_2").child("price").getValue(String.class);
                    String getContact2 = dataSnapshot.child("shalbon").child("hotel_2").child("contact").getValue(String.class);


                    cd_hotel_name.setText(getHotelName);
                    cd_hotel_price.setText(getPrice);
                    cd_hotel_contact.setText(getContact);

                    cd_hotel_name2.setText(getHotelName2);
                    cd_hotel_price2.setText(getPrice2);
                    cd_hotel_contact2.setText(getContact2);


                    details_text.setText(getSiteDetails);
                    todo_tv.setText(getTodoDetails);
                    notTodo_tv.setText(getNotTodoDetails);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String rating = String.valueOf(ratingBar.getRating());
                    Integer rating_num = 0;
                    Toast.makeText(SiteActivity.this, rating + "Star", Toast.LENGTH_SHORT).show();

                    dbReference.child("shalbon").child("Rating").setValue("rating");

                    submit.setEnabled(false);
                }
            });
        }


    }
}