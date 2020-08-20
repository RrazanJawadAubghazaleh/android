package com.movieapp.razan.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImageTranscoderType;
import com.facebook.imagepipeline.core.MemoryChunkType;
import com.movieapp.razan.DetailsActivity;
import com.movieapp.razan.R;
import com.movieapp.razan.home.model.ResultPager;
import com.movieapp.razan.home.ui.viewmodels.HomeViewModel;

import java.util.ArrayList;
import java.util.Collection;

public class RecycleHomeMoviesAdapter extends RecyclerView.Adapter<RecycleHomeMoviesAdapter.MyViewHolder> implements Filterable {

    ArrayList<ResultPager> items;
    ArrayList<ResultPager> moviseListAll;
    private Context context;
    private boolean isCheckrd = false;

    String type = "";

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
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_home, parent, false);
        return new MyViewHolder(itemView);
    }


    //to fill each item with data from the array depending on position
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (items != null) {
            if (items.get(position).getPosterPath() != null) {
                Log.d("movie_id", items.get(position).getPosterPath());
                Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500" + items.get(position).getPosterPath());
                holder.draweeView.setImageURI(uri);
            }
            if (items.get(position).getTitle() != null) {
                holder.tvName.setText(items.get(position).getTitle());
            }


            if (items.get(position).getVoteAverage() != null) {
                holder.tvAvoteAverage.setText(items.get(position).getVoteAverage().toString());
            }
            if (items.get(position).getVoteAverage().toString() != null) {
                holder.tvReatHome.setText(items.get(position).getVoteAverage().toString()+"k");
            }
        }

        if (items.get(position).getGenreIds() != null) {
            getTypeGenre(items.get(position).getGenreIds());
            holder.tvType.setText(type);
        }


        //on click for row
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = items.get(position).getId(); // get Id
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("pos", id); // Pass Id
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    public void getTypeGenre(ArrayList<Integer> g) {
        for (int i = 0; i < g.size(); i++) {
            for (int j = 0; j < HomeViewModel.genresHome.size(); j++) {
                int q = g.get(i);
                if (HomeViewModel.genresHome.get(j).getId() == q)
                    type += HomeViewModel.genresHome.get(j).getName() + "/";
            }

        }
    }


    @Override
    public int getItemCount() {
        if (items == null)
            return 0;

        return items.size();
    }

    public void setList(ArrayList<ResultPager> list) {
        this.items = list;
        this.moviseListAll = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<ResultPager> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredList = moviseListAll;
            } else {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getTitle().toLowerCase()
                            .startsWith(charSequence.toString().toLowerCase())) {
                        filteredList.add(items.get(i));

                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            items.clear();
            items.addAll((Collection<? extends ResultPager>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    //The View Item part responsible for connecting the row.xml with
    // each item in the RecyclerView
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvType, tvAvoteAverage, tvReatHome;
        SimpleDraweeView draweeView, my_heart_home;

        public MyViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.my_image_view_home);
            tvName = itemView.findViewById(R.id.tv_name_movies_home);
            tvType = itemView.findViewById(R.id.tv_type_movies_home);
            tvAvoteAverage = itemView.findViewById(R.id.tv_vote_average_home);
            tvReatHome = itemView.findViewById(R.id.tv_reat_home);
            my_heart_home = (SimpleDraweeView) itemView.findViewById(R.id.my_heart_home);

            my_heart_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isCheckrd) {
                        my_heart_home.setImageResource(R.drawable.heart_oramg);
                        my_heart_home.setMaxWidth(10);
                        my_heart_home.setMaxHeight(10);
                        isCheckrd = false;
                    } else {
                        isCheckrd = true;
                        my_heart_home.setImageResource(R.drawable.heart);
                        my_heart_home.setMaxWidth(10);
                        my_heart_home.setMaxHeight(10);
                    }
                }
            });


        }
    }

}

