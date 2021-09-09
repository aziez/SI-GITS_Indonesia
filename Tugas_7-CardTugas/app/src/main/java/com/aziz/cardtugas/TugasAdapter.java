package com.aziz.cardtugas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TugasAdapter extends RecyclerView.Adapter<TugasAdapter.TugasViewHolder> {

    private ArrayList<Tugas> dataList;

    public TugasAdapter(ArrayList<Tugas> list) {
        this.dataList = list;
    }

    @NonNull
    @Override
    public TugasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tugas, parent, false);

        return new TugasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TugasViewHolder holder, int position) {
      Tugas tugas = dataList.get(position);

      holder.txtTugas.setText(tugas.getNamaTugas());

    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }

    public class TugasViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTugas;

        public TugasViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTugas = itemView.findViewById(R.id.tvTugas);

        }
    }
}
