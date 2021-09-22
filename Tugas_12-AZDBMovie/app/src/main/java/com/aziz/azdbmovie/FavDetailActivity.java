package com.aziz.azdbmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class FavDetailActivity extends AppCompatActivity {
    private TextView txtJudul, txtDesc, txtDate, txtrate;
    private RatingBar ratBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_detail);

        String title = getIntent().getExtras().getString("judul");
        String deskripsi = getIntent().getExtras().getString("desc");
        String release = getIntent().getExtras().getString("date");
        double rating = getIntent().getExtras().getDouble("rate");

        txtJudul = findViewById(R.id.favDetailTitle);
        txtDesc = findViewById(R.id.favDetailDesc);
        txtDate = findViewById(R.id.favDetailDate);
        txtrate = findViewById(R.id.tvFavDetailrating);
        ratBar = findViewById(R.id.favDetailRatingBar);

        txtJudul.setText(title);
        txtDesc.setText(deskripsi);
        txtDate.setText(release);
        txtrate.setText(String.valueOf(rating));
        ratBar.setRating((float) rating);
        ratBar.setNumStars(3);
        ratBar.setStepSize(1);
        ratBar.setEnabled(true);


    }
}