package com.aziz.azdbmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgHero, imgCover;
    private TextView txtJudul, txtDesc;
    private RatingBar ratBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String title = getIntent().getExtras().getString("title");
        String imgRes = getIntent().getExtras().getString("imgUrl");
        String imgCov = getIntent().getExtras().getString("imgCover");
        String descripsi = getIntent().getExtras().getString("dec");
        double rate = getIntent().getExtras().getDouble("rating");




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtDesc = findViewById(R.id.favDetailDesc);
        txtJudul = findViewById(R.id.favDetailTitle);
        imgCover = findViewById(R.id.fav_baner);
        imgHero = findViewById(R.id.fav_hero);
        ratBar = findViewById(R.id.ratingBar);

        txtJudul.setText(title);

        txtDesc.setText(descripsi);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500"+imgRes)
                .into(imgHero);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500"+imgCov)
                .into(imgCover);

        ratBar.setRating((float) rate);


    }
}