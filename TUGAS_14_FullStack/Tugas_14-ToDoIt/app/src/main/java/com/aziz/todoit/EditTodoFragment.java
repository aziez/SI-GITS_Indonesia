package com.aziz.todoit;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aziz.todoit.model.task.DataItem;

import java.util.List;


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



        return view;
    }
}