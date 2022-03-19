package com.example.travelogue;

import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Viewholder> implements Filterable {

    Context context;
    ArrayList<ModelRecycler> recyclerView;
    ArrayList<ModelRecycler> recyclerViewfilter;

    RecyclerAdapter (Context context, ArrayList <ModelRecycler> arrRecycler){
        this.context=context;
        this.recyclerView=arrRecycler;
        this.recyclerViewfilter=arrRecycler;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(R.layout.allplacesrecycler, parent, false);
       Viewholder viewholder= new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.siteName.setText(recyclerViewfilter.get(position).siteName);

    }

    @Override
    public int getItemCount() {

        return recyclerViewfilter.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String character = charSequence.toString();
                if (character.isEmpty()){
                    recyclerViewfilter = recyclerView;
                }
                else {
                    ArrayList<ModelRecycler> filterlist = new ArrayList<>();
                    for (ModelRecycler model: recyclerView){
                        if (model.getSiteName().toLowerCase().contains(character.toLowerCase())){
                            filterlist.add(model);
                        }
                    }

                    recyclerViewfilter = filterlist;
                }

                FilterResults filterResults=new FilterResults();
                filterResults.values = recyclerViewfilter;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                recyclerViewfilter=(ArrayList<ModelRecycler>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView siteName;

        public Viewholder(View itemView){

            super(itemView);

            siteName=itemView.findViewById(R.id.recyclerTextView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position= getAdapterPosition();
            Intent intent = new Intent(context, SiteActivity.class);
            intent.putExtra("site",recyclerViewfilter.get(position).getSiteName());
            context.startActivity(intent);
        }
    }
}
