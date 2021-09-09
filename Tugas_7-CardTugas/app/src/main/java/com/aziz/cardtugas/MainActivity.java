package com.aziz.cardtugas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Tugas> tugasArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rView);
        recyclerView.setHasFixedSize(true);

        tugasArray.addAll(DataTugas.getListTugas());


        showListTugas();


    }

    private void showListTugas() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TugasAdapter tugasAdapter = new TugasAdapter(tugasArray);;

        recyclerView.setAdapter(tugasAdapter);
    }

}