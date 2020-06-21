package com.example.android.popular_movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SelectedMovieActivity extends AppCompatActivity {
    List<Movie> movies;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_movie);
        ImageView poster = (ImageView) findViewById(R.id.iv_poster_selected_movie);
        TextView nameOfTheMovie = (TextView) findViewById(R.id.tv_movie_title);
        TextView releaseYear = (TextView) findViewById(R.id.tv_release_date);
        TextView description = (TextView) findViewById(R.id.tv_movie_description);
        TextView length = (TextView) findViewById(R.id.tv_movie_duration);
        TextView rating = (TextView) findViewById(R.id.tv_movie_rating);

        Intent intent = getIntent();
        Movie movie  = intent.getParcelableExtra("movie");

        nameOfTheMovie.setText(movie.getTitle());
        releaseYear.setText(movie.getYear());
        description.setText(movie.getDescription());
        length.setText(movie.getLength());
        rating.setText(movie.getRating());

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(movie.getImageUrl())
                .into(poster);
    }
}