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

    private Context mContext;
    private ArrayList<String> mImages = new ArrayList<>();
    private OnMovieListener mOnMovieListener;

    public Adapter(Context mContext, ArrayList<String> mImages, OnMovieListener onMovieListener) {
        this.mContext = mContext;
        this.mImages = mImages;
        this.mOnMovieListener = onMovieListener;
    }




    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_list_item,parent, false);
        MoviesViewHolder holder = new MoviesViewHolder(view, mOnMovieListener);
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

    class MoviesViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        ImageView posterImageView;
        OnMovieListener mOnMovieListener;

        public MoviesViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
                super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.iv_poster_main_activity);
            this.mOnMovieListener = onMovieListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnMovieListener.onMovieClick(getAdapterPosition());
        }
    }
    public interface OnMovieListener {
        void onMovieClick(int position);
    }
}
