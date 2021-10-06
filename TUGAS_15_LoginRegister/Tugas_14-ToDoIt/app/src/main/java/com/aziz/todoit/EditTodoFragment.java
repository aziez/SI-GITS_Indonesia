package com.aziz.todoit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditTodoFragment extends DialogFragment {
public EditText task;
public RadioGroup radioGroup;
public Button btnSave;
public RadioButton radioButton;
private final String TAG = "Debug";
private List<DataItem> dataItems;

public EditTodoFragment(){

}

public static EditTodoFragment newInstance (String title){
    EditTodoFragment fragment = new EditTodoFragment();
    Bundle args = new Bundle();
    args.putString("title", title);
    return fragment;
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_edit_todo, container, false);
        btnSave = view.findViewById(R.id.btnEdt);
        task = view.findViewById(R.id.edtText);
        radioGroup = view.findViewById(R.id.radioGroupEdit);
        task = view.findViewById(R.id.edtText);
        radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());

        Integer id = getArguments().getInt("EXTRA_ID");
        String tasks = getArguments().getString("EXTRA_NAMA");

        task.setText(tasks);
        task.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        task.setGravity(Gravity.CENTER_HORIZONTAL);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTask();
            }

            private void EditTask() {
                ApiService apiService = ApiConfig.getApiService();

                apiService.editTodo(id, task.getText().toString(), radioButton.getText().toString()).enqueue(new Callback<TaskModel>() {
                    @Override
                    public void onResponse(Call<TaskModel> call, Response<TaskModel> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(view.getContext(), "berhasil Memperbaharui", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(view.getContext(), MainActivity.class);
                            getContext().startActivity(i);
                            ((MainActivity)getContext()).finish();
                        }else{
                            Toast.makeText(view.getContext(), "gagal Memperbaharui", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TaskModel> call, Throwable t) {
                        Toast.makeText(view.getContext(), "masalah Sambungan"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Failure", "onFailure: Gagal Edit"+ t.getMessage());

                    }
                });
            }
        });


        return view;
    }
}