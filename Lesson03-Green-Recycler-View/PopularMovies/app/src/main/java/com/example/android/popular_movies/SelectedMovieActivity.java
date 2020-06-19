package com.example.android.popular_movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SelectedMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_movie);
        Intent intentStartedThisActivity = getIntent();
        if(intentStartedThisActivity.hasExtra("name_of_the_movie")){

        }
    }
}