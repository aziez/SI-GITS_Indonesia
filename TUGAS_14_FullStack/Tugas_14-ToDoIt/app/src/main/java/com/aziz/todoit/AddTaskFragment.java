package com.aziz.todoit;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aziz.todoit.model.task.DataItem;
import com.aziz.todoit.model.task.TaskModel;
import com.aziz.todoit.rest.ApiConfig;
import com.aziz.todoit.rest.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddTaskFragment extends DialogFragment {
    public EditText tasks;
    public RadioGroup radioGroup;
    public Button btnSave;
    public RadioButton statusBtn;
    private final String TAG = "debug";

    private List<DataItem> dataItemLiveData;

    public AddTaskFragment() {

    }

    public static AddTaskFragment newInstance(String title){
        AddTaskFragment fragment = new AddTaskFragment();
        Bundle args = new Bundle();
        args.putString("Title", title);
        return  fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_task, container, false);

        btnSave = view.findViewById(R.id.btnEdt);
        tasks = view.findViewById(R.id.addText);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                addTask();
            }

            private void addTask() {
                radioGroup = view.findViewById(R.id.radioGroup);
                statusBtn = view.findViewById(radioGroup.getCheckedRadioButtonId());

                String task = tasks.getText().toString();
                String stats = statusBtn.getText().toString();

                ApiService apiService = ApiConfig.getApiService();
                apiService.postAddTask(task, stats).enqueue(new Callback<ArrayList<TaskModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<TaskModel>> call, Response<ArrayList<TaskModel>> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Gagal Menyimpan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<TaskModel>> call, Throwable t) {
                        Toast.makeText(getContext(), "Berhasil Menambah Data", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}