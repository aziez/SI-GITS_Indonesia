package com.aziz.todoit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aziz.todoit.adapter.SliderAdapter;
import com.aziz.todoit.adapter.TaskAdapter;
import com.aziz.todoit.model.task.DataItem;
import com.aziz.todoit.model.task.TaskModel;
import com.aziz.todoit.rest.ApiConfig;
import com.aziz.todoit.rest.ApiService;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TaskFragment extends Fragment {
private final String TAG = "debug";
private RecyclerView rv;
private FrameLayout frameLayout;
private ViewPager viewPager;
private List<DataItem> dataItems;
private Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_task, container, false);
        rv = view.findViewById(R.id.rvTask);
        viewPager = view.findViewById(R.id.slider_Pager);



        getConnection();
        return view;
    }

    private void getConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getActiveNetworkInfo() != null){
            getTask();

        }
    }

    private void getTask() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.listTodo().enqueue(new Callback<TaskModel>() {
            @Override
            public void onResponse(Call<TaskModel> call, Response<TaskModel> response) {
                Log.d(TAG, "OnResponse" + response.body());

                if (response.isSuccessful()){
                    dataItems = new ArrayList<>();
                    dataItems = response.body().getData();

                    TaskAdapter taskAdapter = new TaskAdapter(getActivity(), dataItems);
                    SliderAdapter sliderAdapter = new SliderAdapter(getActivity(), dataItems);

                    viewPager.setAdapter(sliderAdapter);


                    rv.setAdapter(taskAdapter);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

                }else{
                    Toast.makeText(getContext(), "gagal Mengambil Data", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TaskModel> call, Throwable t) {
                Toast.makeText(getContext(), "Cek Koneksi Ke Database", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }
}