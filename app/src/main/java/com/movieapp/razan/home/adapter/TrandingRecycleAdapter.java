package com.movieapp.razan.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.movieapp.razan.R;
import com.movieapp.razan.databinding.RowItemTrandingBBinding;
import com.movieapp.razan.home.model.Result;

import java.util.ArrayList;
import java.util.Locale;

public class TrandingRecycleAdapter extends RecyclerView.Adapter<TrandingRecycleAdapter.MyviewHolder> {

    private Context context;
    private ArrayList<Result>results=new ArrayList<>();

    RowItemTrandingBBinding rowItemTrandingBBinding;
    public TrandingRecycleAdapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RowItemTrandingBBinding rowItemTrandingBBinding=DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_item_tranding_b,parent,false);
        MyviewHolder myviewHolder=new MyviewHolder(rowItemTrandingBBinding);
        return  myviewHolder;

       /* rowItemTrandingBBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_item_tranding_b,parent,false);
        MyviewHolder myviewHolder=new MyviewHolder(rowItemTrandingBBinding);
        return myviewHolder;*/
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        Result result=results.get(position);
        holder.rowItemTrandingBBinding.setResult(result);
        holder.rowItemTrandingBBinding.myImageView.setImageURI( Uri.parse("https://image.tmdb.org/t/p/w500"+result.getPosterPath()));

       /* Log.d("movie_id", items.get(position).getPosterPath());
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w500"+items.get(position).getPosterPath());
        holder.draweeView.setImageURI(uri);*/

    }

    @Override
    public int getItemCount() {
        if (results == null)
            return 0;

        return results.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {

        RowItemTrandingBBinding rowItemTrandingBBinding;

        public MyviewHolder(@NonNull RowItemTrandingBBinding rowItemTrandingBBinding) {
            super(rowItemTrandingBBinding.getRoot());
           this.rowItemTrandingBBinding=rowItemTrandingBBinding;
        }
    }
}
