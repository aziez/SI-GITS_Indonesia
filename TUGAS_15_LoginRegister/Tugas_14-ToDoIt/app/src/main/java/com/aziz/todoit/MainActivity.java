package com.aziz.todoit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, new TaskFragment()).commit();

        FloatingActionButton fabButon = findViewById(R.id.fab);

        fabButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowEditDialog();
            }
        });


    }

    private void ShowEditDialog() {
        fragmentManager = getSupportFragmentManager();
        AddTaskFragment addTaskFragment = AddTaskFragment.newInstance("Add Task");
        addTaskFragment.show(fragmentManager, "addTaskFragment");
    }
}