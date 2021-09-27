package com.aziz.todoit.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.todoit.MainActivity;
import com.aziz.todoit.R;
import com.aziz.todoit.model.task.DataItem;

import com.aziz.todoit.model.task.TaskModel;
import com.aziz.todoit.rest.ApiConfig;
import com.aziz.todoit.rest.ApiService;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    Context context;
    List<DataItem> itemTask;

    public TaskAdapter(Context context, List<DataItem> itemTask) {
        this.context = context;
        this.itemTask = itemTask;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTask.setText(itemTask.get(position).getTaskName());
        holder.tvStatus.setText(itemTask.get(position).getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());
                bottomSheetDialog.setContentView(R.layout.bootom_sheet);

                LinearLayout delete = bottomSheetDialog.findViewById(R.id.delete);
                LinearLayout edit = bottomSheetDialog.findViewById(R.id.edit);

                bottomSheetDialog.show();

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer id = itemTask.get(position).getId();

                        ApiService apiService = ApiConfig.getApiService();
                        apiService.deleteTodo(id).enqueue(new Callback<TaskModel>() {
                            @Override
                            public void onResponse(Call<TaskModel> call, Response<TaskModel> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(v.getContext(), "Berhasil Menghapus", Toast.LENGTH_SHORT).show();
                                    bottomSheetDialog.dismiss();
                                    Intent i = new Intent(v.getContext(), MainActivity.class);
                                    context.startActivity(i);
                                    ((MainActivity)context).finish();

                                }else {
                                    Toast.makeText(v.getContext(), "gagal Menghapus", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<TaskModel> call, Throwable t) {
                                Toast.makeText(v.getContext(), "masalah Sambungan"+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Edit Dalam Pengembangan", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }

    @Override
    public int getItemCount() {
        return itemTask.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTask,tvStatus;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTask = itemView.findViewById(R.id.tvTask);
            tvStatus = itemView.findViewById(R.id.tvStatus);


        }
    }
}
