package com.aziz.tugas_9_vrweb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.tugas_9_vrweb.R;
import com.aziz.tugas_9_vrweb.data.Projek;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListProjekAdapter extends RecyclerView.Adapter<ListProjekAdapter.ListViewHolder> {
    private ArrayList<Projek> listProjek;

    public ListProjekAdapter(ArrayList<Projek> list) {
        this.listProjek = list;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Projek projek = listProjek.get(position);

        Glide.with(holder.itemView.getContext())
                .load(projek.getHero())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.heroImg);

        holder.tvName.setText(projek.getNama());

    }

    @Override
    public int getItemCount() {
        int size = listProjek.size();

        return size;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView heroImg;
        TextView tvName;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            heroImg = itemView.findViewById(R.id.img_hero);
            tvName = itemView.findViewById(R.id.tv_item_name);
        }
    }
}
