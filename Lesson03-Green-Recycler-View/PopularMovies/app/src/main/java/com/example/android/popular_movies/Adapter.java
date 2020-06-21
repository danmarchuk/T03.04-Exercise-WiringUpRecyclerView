package com.example.android.popular_movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MoviesViewHolder> {

    private List<Movie> movies;
    private Context mContext;
    private OnMovieListener mOnMovieListener;

    public Adapter(Context mContext, List<Movie> movie, OnMovieListener onMovieListener) {
        this.mContext = mContext;
        this.movies = movie;
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
                .load(movies.get(position).getImageUrl())
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
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
