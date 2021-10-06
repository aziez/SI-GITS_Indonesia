package com.aziz.todoit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.aziz.todoit.model.user.DataItem;
import com.aziz.todoit.model.user.UserModel;
import com.aziz.todoit.rest.ApiConfig;
import com.aziz.todoit.rest.ApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = "debug";
    private TextInputEditText txusername,txemail,txpass;
    private TextView regisBtn;
    private CheckBox checkBox;
    private Context context;
    private List<DataItem> dataItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;

        txusername = findViewById(R.id.username);
        txemail = findViewById(R.id.inputEmail);
        txpass = findViewById(R.id.inputpass);
        checkBox = findViewById(R.id.chekya);
        regisBtn = findViewById(R.id.btnRegis);

            regisBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestRegister();
                }
            });

    }

    private void requestRegister() {
        String username = txusername.getText().toString();
        String email = txemail.getText().toString();
        String pass = txpass.getText().toString();

        if (validateRegister(username, email, pass)){
            ApiService apiService = ApiConfig.getApiService();
            apiService.postRegister(username, email, pass)
                    .enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(context.getApplicationContext(), "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(context.getApplicationContext(), LoginActivity.class));
                                finish();
                            }else{
                                Toast.makeText(context.getApplicationContext(), "Username Telah dipakai", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {
                            Log.d(TAG, "onFailure: GAGAL DI DATABASE "+t.getMessage());
                        }
                    });
        }
    }


    private boolean validateRegister(String username, String email, String password) {
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(email == null || email.trim().length() == 0){
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}