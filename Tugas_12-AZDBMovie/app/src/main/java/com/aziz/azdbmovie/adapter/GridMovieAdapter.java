package com.aziz.azdbmovie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.azdbmovie.DetailActivity;
import com.aziz.azdbmovie.R;
import com.aziz.azdbmovie.model.MovieModel;
import com.aziz.azdbmovie.model.ResultsItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

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
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageViewGrid);
        }
    }
}
