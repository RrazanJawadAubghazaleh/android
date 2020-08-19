package com.movieapp.razan.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImageTranscoderType;
import com.facebook.imagepipeline.core.MemoryChunkType;
import com.movieapp.razan.R;
import com.movieapp.razan.home.model.Meal;
import com.movieapp.razan.home.model.Result;
import com.movieapp.razan.home.model.ResultPager;

import java.util.ArrayList;

public class RecycleHomeMoviesAdapter extends RecyclerView.Adapter<RecycleHomeMoviesAdapter.viewitem> {

    ArrayList<ResultPager> items;
    ArrayList<Meal>meals;
    private Context context;
    private boolean isCheckrd = false;

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
       Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500" + items.get(position).getPosterPath());

        holder.draweeView.setImageURI(uri);
           holder.tvName.setText(items.get(position).getTitle());
        holder.tvType.setText("action");
        holder.tvAvoteAverage.setText(items.get(position).getVoteAverage().toString());
        holder.tvReatHome.setText(items.get(position).getVoteAverage().toString());

        // holder.tvName.setText(meals.get(position).getMealName());
        //  holder.tvType.setText(meals.get(position).getMealPrice());
        //    holder.tvAvoteAverage.setText(items.get(position).getVoteAverage().toString());
        // holder.tvReatHome.setText(items.get(position).getVoteAverage().toString());
    }


    @Override
    public int getItemCount() {
        if (items == null)
            return 0;

        return items.size();
    }

    public void setList(ArrayList<ResultPager> list) {
        this.items = list;
        notifyDataSetChanged();
    }


    public void setListMeal(ArrayList<Meal> list) {
        this.meals = list;
        notifyDataSetChanged();
    }

    public void addPlayers(ArrayList<ResultPager> datum) {
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
        TextView tvName, tvType, tvAvoteAverage, tvReatHome;
        SimpleDraweeView draweeView, my_heart_home;

        //initialize
        public viewitem(View itemView) {
            super(itemView);
            //image = itemView.findViewById(R.id.img_trending);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.my_image_view_home);

            tvName = itemView.findViewById(R.id.tv_name_movies_home);
            tvType = itemView.findViewById(R.id.tv_type_movies_home);
            tvAvoteAverage = itemView.findViewById(R.id.tv_vote_average_home);
            tvReatHome = itemView.findViewById(R.id.tv_reat_home);
            my_heart_home = (SimpleDraweeView) itemView.findViewById(R.id.my_heart_home);

          /*  my_heart_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isCheckrd) {
                        my_heart_home.setImageResource(R.drawable.heart_oramg);
                        isCheckrd = false;
                    } else {
                        isCheckrd = true;
                        my_heart_home.setImageResource(R.drawable.heart);
                    }
                }
            });

*/
        }
    }

}

