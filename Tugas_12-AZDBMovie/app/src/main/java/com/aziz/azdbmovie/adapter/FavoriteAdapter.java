package com.aziz.azdbmovie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.azdbmovie.FavDetailActivity;
import com.aziz.azdbmovie.R;
import com.aziz.azdbmovie.database.entitas.Movies;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {
    List<Movies> moviesList;
    Context context;

    public FavoriteAdapter(List<Movies> movies) {
        this.moviesList = movies;
    }


    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        FavoriteHolder favoriteHolder = new FavoriteHolder(view);

        return favoriteHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTitle.setText(String.valueOf(moviesList.get(position).judul));
        holder.tvDate.setText(String.valueOf(moviesList.get(position).releasedate));
        holder.tvDesc.setText(String.valueOf(moviesList.get(position).overview));
        holder.rating.setText(String.valueOf(moviesList.get(position).rate));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Anda Memilih"+moviesList.get(position).judul, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), FavDetailActivity.class);
                i.putExtra("judul", moviesList.get(position).judul);
                i.putExtra("desc", moviesList.get(position).overview);
                i.putExtra("date", moviesList.get(position).releasedate);
                i.putExtra("rate", moviesList.get(position).rate);
                v.getContext().startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDate, rating, tvDesc;
        ImageView imgHero, imgBaner;
        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_fav_judul);
            tvDesc = itemView.findViewById(R.id.tv_fav_ket);
            tvDate = itemView.findViewById(R.id.tv_fav_date);
            imgHero = itemView.findViewById(R.id.fav_hero);
            imgBaner = itemView.findViewById(R.id.fav_baner);
            rating = itemView.findViewById(R.id.tv_fav_rating);

        }
    }
}
