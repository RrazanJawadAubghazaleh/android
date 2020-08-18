package com.movieapp.razan.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImageTranscoderType;
import com.facebook.imagepipeline.core.MemoryChunkType;
import com.movieapp.razan.R;
import com.movieapp.razan.home.model.Result;

import java.util.ArrayList;

public class RecycleHomeMoviesAdapter extends RecyclerView.Adapter<RecycleHomeMoviesAdapter.viewitem> {


    ArrayList<Result> items;
    private Context context;


    public RecycleHomeMoviesAdapter(Context context) {
        this.context = context;
        Fresco.initialize(
                context,
                ImagePipelineConfig.newBuilder(context)
                        .setMemoryChunkType(MemoryChunkType.BUFFER_MEMORY)
                        .setImageTranscoderType(ImageTranscoderType.JAVA_TRANSCODER)
                        .experiment().setNativeCodeDisabled(true)
                        .build());
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

        Log.d("movie_id", items.get(position).getPosterPath());
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500"+items.get(position).getPosterPath());
        holder.draweeView.setImageURI(uri);


    }


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
       // ImageView image;
        SimpleDraweeView draweeView ;
        //initialize
        public viewitem(View itemView) {
            super(itemView);
            //image = itemView.findViewById(R.id.img_trending);
            draweeView= (SimpleDraweeView) itemView.findViewById(R.id.my_image_view_home);

        }
    }

}

