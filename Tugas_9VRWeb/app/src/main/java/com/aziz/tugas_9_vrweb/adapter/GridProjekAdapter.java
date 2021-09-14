package com.aziz.tugas_9_vrweb.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.tugas_9_vrweb.R;
import com.aziz.tugas_9_vrweb.ViewProjekActivity;
import com.aziz.tugas_9_vrweb.data.Projek;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridProjekAdapter extends RecyclerView.Adapter<GridProjekAdapter.GridViewHolder> {
    private ArrayList<Projek> listProjek;
    private String url = "EXTRA URL";
    private String hero = "HERO EXTRA";
    private String nama = "NAMA EXTRA";

    public GridProjekAdapter(ArrayList<Projek> listProjek) {
        this.listProjek = listProjek;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_row, parent, false);

        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(listProjek.get(position).getHero())
                .apply(new RequestOptions().override(350, 350))
                .into(holder.heroImg);

        holder.tvprojek.setText(listProjek.get(position).getNama());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = listProjek.get(holder.getAdapterPosition()).getUrl();
                Toast.makeText(holder.itemView.getContext(), "Membuka " + url, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(v.getContext(), ViewProjekActivity.class);
                i.putExtra("EXTRAURL", url);

                v.getContext().startActivity(i);
            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = listProjek.get(holder.getAdapterPosition()).getUrl();
                nama = listProjek.get(holder.getAdapterPosition()).getNama();

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, nama + url);
                shareIntent.setType("text/plain");

                Intent seendIntent = Intent.createChooser(shareIntent, null);
                v.getContext().startActivity(seendIntent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return listProjek.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView heroImg;
        TextView tvprojek;
        Button button;
        Button btnShare;


        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            heroImg = itemView.findViewById(R.id.img_hero);
            tvprojek = itemView.findViewById(R.id.nameGrid);
            button = itemView.findViewById(R.id.btnView);
            btnShare = itemView.findViewById(R.id.btnShare);

        }
    }
}
