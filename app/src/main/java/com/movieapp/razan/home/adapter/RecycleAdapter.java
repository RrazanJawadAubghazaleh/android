package com.movieapp.razan.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.movieapp.razan.R;
import com.movieapp.razan.home.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.viewitem> {


    ArrayList<Result> items;
    private static String PLAYER_ID_KEY = "Player_id_key";
    private Context context;

    public RecycleAdapter(ArrayList<Result> item) {
        items = item;

    }

    public RecycleAdapter(Context context) {
        this.context = context;
        // notifyDataSetChanged();
    }


    //onCreateViewHolder used to HAndle on Clicks
    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {

        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_tranding, parent, false);
        return new viewitem(itemView);
    }


    //to fill each item with data from the array depending on position
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final viewitem holder, final int position) {

        //Glide.with(context).load("https://cdn2.thecatapi.com/images/" + items.get(position).getId()).into(holder.image);

        Log.d("movie_id", items.get(position).getPosterPath());
          Picasso.get().load("https://image.tmdb.org/t/p/w500"+items.get(position).getPosterPath()).into(holder.image);

    }
    //https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;

        return items.size();
    }

    public void setList(ArrayList<Result> list) {
        this.items = list;
        notifyDataSetChanged();
    }


    public void addPlayers(ArrayList<Result> datum) {
        if (items == null) {
            this.items = datum;
            notifyDataSetChanged();
        } else {
            this.items.addAll(datum);
            notifyDataSetChanged();
        }


    }

    //The View Item part responsible for connecting the row.xml with
    // each item in the RecyclerView
    //make declare and initalize
    class viewitem extends RecyclerView.ViewHolder {

        //Declare
        TextView textViewName, textViewTeam;
        ImageView image;
        CardView cardView;


        //initialize
        public viewitem(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_trending);

        }
    }

}

