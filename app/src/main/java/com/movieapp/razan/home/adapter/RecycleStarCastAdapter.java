package com.movieapp.razan.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.movieapp.razan.DetailsActivity;
import com.movieapp.razan.R;
import com.movieapp.razan.home.model.Cast;
import com.movieapp.razan.home.model.Result;

import java.util.ArrayList;

public class RecycleStarCastAdapter extends RecyclerView.Adapter<RecycleStarCastAdapter.viewitem> {


    ArrayList<Cast> items;
    private Context context;


    public RecycleStarCastAdapter(Context context) {
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
                .inflate(R.layout.row_item_start_cast, parent, false);
        return new viewitem(itemView);
    }


    //to fill each item with data from the array depending on position
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final viewitem holder, final int position) {

        if (items.get(position).getProfilePath() != null) {

            Log.d("movie_id", items.get(position).getProfilePath());
            Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500"+items.get(position).getProfilePath());
            holder.draweeView.setImageURI(uri);
        }



    }


    @Override
    public int getItemCount() {
        if (items == null)
            return 0;

        return items.size();
    }

    public void setList(ArrayList<Cast> list) {
        this.items = list;
        notifyDataSetChanged();
    }

    class viewitem extends RecyclerView.ViewHolder {

        //Declare
       // ImageView image;
        SimpleDraweeView draweeView ;
        //initialize
        public viewitem(View itemView) {
            super(itemView);
            draweeView= (SimpleDraweeView) itemView.findViewById(R.id.my_image_view_detail_Cast);

        }
    }

}

