package com.aziz.azdbmovie.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aziz.azdbmovie.R;
import com.aziz.azdbmovie.adapter.GridMovieAdapter;
import com.aziz.azdbmovie.adapter.NowPlayingAdapter;
import com.aziz.azdbmovie.adapter.SliderAdapter;
import com.aziz.azdbmovie.api.ApiConfig;
import com.aziz.azdbmovie.api.ApiService;
import com.aziz.azdbmovie.model.MovieModel;
import com.aziz.azdbmovie.model.ResultsItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private final String TAG = "debug";
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private RecyclerView rv;
    private RecyclerView rv2;
    private List<ResultsItem> resList;
    private  View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.slider_Pager);
        tabLayout = view.findViewById(R.id.indicator);
        rv = view.findViewById(R.id.Rv_movies);
        rv2 = view.findViewById(R.id.rv_allMovie);


        GetUpcoming();
        getNowPlaying();
        getPopularMovie();

        return view;
    }

    private void getPopularMovie() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.PopularMovie().enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                Log.d(TAG, "OnResponse" + response.body());

                if (response.isSuccessful()){
                    resList = new ArrayList<>();
                    resList = response.body().getResults();

                    rv2.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
                    GridMovieAdapter gridMovieAdapter = new GridMovieAdapter(view.getContext(), resList, this);
                    rv2.setAdapter(gridMovieAdapter);


                }else{
                    Toast.makeText(view.getContext(), "gagal Mengambil Data ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toast.makeText(view.getContext(), "Cek Koneksi Anda "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "OnFailure" + t.getMessage());
            }
        });
    }

    private void getNowPlaying() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.nowPlaying().enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                Log.d(TAG, "OnResponse" + response.body());

                if (response.isSuccessful()){
                    resList = new ArrayList<>();
                    resList = response.body().getResults();

                    NowPlayingAdapter nowPlayingAdapter = new NowPlayingAdapter(view.getContext(), resList);
                    rv.setAdapter(nowPlayingAdapter);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                }else{
                    Toast.makeText(view.getContext(), "gagal Mengambil Data ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toast.makeText(view.getContext(), "Cek Koneksi Anda "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "OnFailure" + t.getMessage());
            }
        });
    }

    private void GetUpcoming() {
        ApiService service = ApiConfig.getApiService();
        service.upComing().enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                Log.d(TAG, "onResponse " + response.body());

                if (response.isSuccessful()) {
                    resList = new ArrayList<>();
                    resList = response.body().getResults();

                    SliderAdapter sliderAdapter = new SliderAdapter(getActivity(), resList);
                    viewPager.setAdapter(sliderAdapter);
                    tabLayout.setupWithViewPager(viewPager, true);
                } else {
                    Toast.makeText(view.getContext(), "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toast.makeText(view.getContext(), "Cek Koneksi Anda "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "OnFailure" + t.getMessage());
            }
        });

    }

}