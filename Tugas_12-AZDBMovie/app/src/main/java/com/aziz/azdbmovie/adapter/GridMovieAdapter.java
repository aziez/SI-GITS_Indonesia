package com.aziz.azdbmovie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.azdbmovie.DetailActivity;
import com.aziz.azdbmovie.MainActivity;
import com.aziz.azdbmovie.R;
import com.aziz.azdbmovie.database.AppDatabase;
import com.aziz.azdbmovie.database.entitas.Movies;
import com.aziz.azdbmovie.model.MovieModel;
import com.aziz.azdbmovie.model.ResultsItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Callback;

public class GridMovieAdapter extends RecyclerView.Adapter<GridMovieAdapter.MovieHolder> {
    Context context;
    View view;
    List<ResultsItem> resList;


    public GridMovieAdapter(Context context, List<ResultsItem> resList, Callback<MovieModel> gClick) {
        this.context = context;
        this.resList = resList;
    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.movie_grid, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, @SuppressLint("RecyclerView") int position) {


        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+resList.get(position).getPosterPath())
                .apply(new RequestOptions().override(200, 250))
                .into(holder.imgView);

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String judulFilm = resList.get(position).getTitle();
                String ket = resList.get(position).getOverview();
                String release = resList.get(position).getReleaseDate();
                double rateingFilm = resList.get(position).getVoteAverage();
//                String heroString = resList.get(position).getPosterPath();




                Movies movies = new Movies();

                movies.judul = judulFilm;
                movies.overview = ket;
                movies.releasedate = release;
                movies.rate = rateingFilm;
//                movies.imgHero = heroString;
                


                MainActivity.database.userDao().insertAll(movies);
                Toast.makeText(context.getApplicationContext(), "Berhasil Menambah "+ movies.judul, Toast.LENGTH_SHORT).show();
            }


        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("title", resList.get(position).getTitle());
                intent.putExtra("imgUrl", resList.get(position).getPosterPath());
                intent.putExtra("imgCover", resList.get(position).getBackdropPath());
                intent.putExtra("dec", resList.get(position).getOverview());
                intent.putExtra("rating", resList.get(position).getVoteAverage());

                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "Anda Memilih Film : "+ resList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
      return resList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        private ImageView imgView;
        private ImageButton fav;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageViewGrid);
            fav = itemView.findViewById(R.id.btn_fav);
        }
    }
}
