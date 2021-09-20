package com.aziz.azdbmovie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.azdbmovie.DetailActivity;
import com.aziz.azdbmovie.R;
import com.aziz.azdbmovie.model.ResultsItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {
    Context context;
    View view;
    List<ResultsItem> resLis;

    public NowPlayingAdapter(Context context, List<ResultsItem> resLis) {
        this.context = context;
        this.resLis = resLis;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvjudul.setText(resLis.get(position).getTitle());

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+resLis.get(position).getPosterPath())
                .fitCenter()
                .into(holder.imgHero);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("title", resLis.get(position).getTitle());
                intent.putExtra("imgUrl", resLis.get(position).getPosterPath());
                intent.putExtra("imgCover", resLis.get(position).getBackdropPath());
                intent.putExtra("dec", resLis.get(position).getOverview());
                intent.putExtra("rating", resLis.get(position).getVoteAverage());

                view.getContext().startActivity(intent);


                Toast.makeText(view.getContext(), "Anda Memilih" + resLis.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return resLis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvjudul;
        private ImageView imgHero;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvjudul = itemView.findViewById(R.id.tvJudullist);
            imgHero = itemView.findViewById(R.id.item_movie_img);


        }
    }
}
