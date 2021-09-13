package com.aziz.tugas_9_vrweb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aziz.tugas_9_vrweb.adapter.ListProjekAdapter;
import com.aziz.tugas_9_vrweb.data.DataProjek;
import com.aziz.tugas_9_vrweb.data.Projek;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private ArrayList<Projek> list = new ArrayList<>();

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);

        list.addAll(DataProjek.getDataProjek());
        showListProjek();

        return view;
    }

    private void showListProjek() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListProjekAdapter listProjekAdapter = new ListProjekAdapter(list);
        recyclerView.setAdapter(listProjekAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}