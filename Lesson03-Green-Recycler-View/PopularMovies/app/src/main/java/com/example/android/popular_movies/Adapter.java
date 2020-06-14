package com.example.android.popular_movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MoviesViewHolder> {


    public Adapter(Context mContext, ArrayList<String> mImages) {
        this.mContext = mContext;
        this.mImages = mImages;
    }

    private Context mContext;
    private ArrayList<String> mImages = new ArrayList<>();



    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_list_item,parent, false);
        MoviesViewHolder holder = new MoviesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.iv_poster_main_activity);
        }
    }
}
