package com.aziz.tugas_9_vrweb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aziz.tugas_9_vrweb.adapter.GridProjekAdapter;
import com.aziz.tugas_9_vrweb.adapter.ListProjekAdapter;
import com.aziz.tugas_9_vrweb.data.DataProjek;
import com.aziz.tugas_9_vrweb.data.Projek;

import java.util.ArrayList;


public class GridFragment extends Fragment {
    private ArrayList<Projek> list = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_grid, container, false);

        recyclerView = view.findViewById(R.id.rv_grid);
        recyclerView.setHasFixedSize(true);

        list.addAll(DataProjek.getDataProjek());
        showGridProjek();

        return view;

    }

    private void showGridProjek() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridProjekAdapter gridProjekAdapter = new GridProjekAdapter(list);
        recyclerView.setAdapter(gridProjekAdapter);
    }
}