package com.aziz.azdbmovie.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aziz.azdbmovie.MainActivity;
import com.aziz.azdbmovie.R;
import com.aziz.azdbmovie.adapter.FavoriteAdapter;
import com.aziz.azdbmovie.database.entitas.Movies;

import java.util.List;


public class FavoritFragment extends Fragment {
    private RecyclerView rvFav;
    private RecyclerView.LayoutManager layoutManager;
    FavoriteAdapter favoriteAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_favorit, container, false);
        rvFav = view.findViewById(R.id.rvFavorite);
        layoutManager = new LinearLayoutManager(getActivity());

        rvFav.setLayoutManager(layoutManager);
        List<Movies> moviesList = MainActivity.database.userDao().getAll();

        favoriteAdapter = new FavoriteAdapter(moviesList);
        rvFav.setAdapter(favoriteAdapter);


        return view;
    }

}