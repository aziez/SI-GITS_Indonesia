package com.aziz.todoit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aziz.todoit.adapter.TaskAdapter;
import com.aziz.todoit.model.task.DataItem;
import com.aziz.todoit.model.task.TaskModel;
import com.aziz.todoit.rest.ApiConfig;
import com.aziz.todoit.rest.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TaskFragment extends Fragment {
private final String TAG = "debug";
private RecyclerView rv;
private List<DataItem> dataItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_task, container, false);
        rv = view.findViewById(R.id.rvTask);

        getConnection();
        return view;
    }

    private void getConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getActiveNetworkInfo() != null){
            Toast.makeText(getActivity(), "Internet Terhubung", Toast.LENGTH_SHORT).show();
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

                    TaskAdapter taskAdapter = new TaskAdapter(getContext(), dataItems);
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