package com.example.travelogue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AllPlacesActivity extends AppCompatActivity {

    SearchView allPlacesSearchView;

    ArrayList<ModelRecycler> recyclerView=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_places);

        allPlacesSearchView=findViewById(R.id.allPlacesSearchView);

        RecyclerView allPlacesrecycler=findViewById(R.id.allPlacesRecycler);
        allPlacesrecycler.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.add(new ModelRecycler("Kuakata"));
        recyclerView.add(new ModelRecycler("Cox's Bazar"));
        recyclerView.add(new ModelRecycler("Sylhet"));
        recyclerView.add(new ModelRecycler("Rangamati"));
        recyclerView.add(new ModelRecycler("Sreemangal"));
        recyclerView.add(new ModelRecycler("Kewkradong"));
        recyclerView.add(new ModelRecycler("Khulna"));
        recyclerView.add(new ModelRecycler("Saint Martin Island"));
        recyclerView.add(new ModelRecycler("Sundarban"));
        recyclerView.add(new ModelRecycler("Barisal"));
        recyclerView.add(new ModelRecycler("Sajek"));
        recyclerView.add(new ModelRecycler("Sitakundu"));
        recyclerView.add(new ModelRecycler("Dhaka"));
        recyclerView.add(new ModelRecycler("Kishoreganj"));
        recyclerView.add(new ModelRecycler("Rajshahi"));
        recyclerView.add(new ModelRecycler("Chattogram"));
        recyclerView.add(new ModelRecycler("Bagerhat"));
        recyclerView.add(new ModelRecycler("Gazipur"));
        recyclerView.add(new ModelRecycler("Panchagarh"));
        recyclerView.add(new ModelRecycler("Tangail"));
        recyclerView.add(new ModelRecycler("Ahsan Manjil"));
        recyclerView.add(new ModelRecycler("The American Church"));
        recyclerView.add(new ModelRecycler("Bandarban"));
        recyclerView.add(new ModelRecycler("Lal Bagh Fort"));
        recyclerView.add(new ModelRecycler("Sompura Mahavira"));
        recyclerView.add(new ModelRecycler("Khagrachari"));;
        recyclerView.add(new ModelRecycler("The Liberation War Museum"));
        recyclerView.add(new ModelRecycler("Shat Gambuj Mosque"));
        recyclerView.add(new ModelRecycler("Panam City"));
        recyclerView.add(new ModelRecycler("Jashore"));
        recyclerView.add(new ModelRecycler("Mohasthangarh"));
        recyclerView.add(new ModelRecycler("Bogalake"));
        recyclerView.add(new ModelRecycler("Bangladesh National Museum"));
        recyclerView.add(new ModelRecycler("Novo Theatre"));
        recyclerView.add(new ModelRecycler("Gopalganj"));
        recyclerView.add(new ModelRecycler("Bangabandhu Memorial Museum"));
        recyclerView.add(new ModelRecycler("The Liberation War Museum"));
        recyclerView.add(new ModelRecycler("Bangladesh Military Museum"));
        recyclerView.add(new ModelRecycler("Varendra Research Museum"));
        recyclerView.add(new ModelRecycler("Independence Museum"));
        recyclerView.add(new ModelRecycler("Baitul Mukarram"));
        recyclerView.add(new ModelRecycler("Dhakeshwari Mandir"));
        recyclerView.add(new ModelRecycler("National Memorial"));
        recyclerView.add(new ModelRecycler("Rabindranath's Kuthibari"));
        recyclerView.add(new ModelRecycler("Kotila Mura"));
        recyclerView.add(new ModelRecycler("Old Dhaka"));
        recyclerView.add(new ModelRecycler("Atiya Mosque"));
        recyclerView.add(new ModelRecycler("Shalbon Bihar"));

        RecyclerAdapter adapter = new RecyclerAdapter(this, recyclerView);
        allPlacesrecycler.setAdapter(adapter);

        allPlacesSearchView.setMaxWidth(Integer.MAX_VALUE);
        allPlacesSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

}