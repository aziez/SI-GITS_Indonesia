package com.aziz.todoit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aziz.todoit.model.user.DataItem;
import com.aziz.todoit.rest.ApiConfig;
import com.aziz.todoit.rest.ApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity { 
    private final String TAG = "debug";
    private TextInputEditText txusername,txpass;
    private TextView tvRegis;
    private Button btnLogin;
    private Context context;
    private List<DataItem> dataItems;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;


        txusername = findViewById(R.id.username);
        txpass = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginBtn);

        tvRegis = findViewById(R.id.tvRegis);

        tvRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }
        });

    }

    private void requestLogin() {
      String username = txusername.getText().toString();
      String password = txpass.getText().toString();
        if(validateLogin(username, password)){
            //do login
            doLogin(username, password);
        }



    }

    private void doLogin( final String username, final String password) {
        ApiService apiService = ApiConfig.getApiService();
        Call<DataItem> login = apiService.login(username, password);
        login.enqueue(new Callback<DataItem>() {
            @Override
            public void onResponse(Call<DataItem> call, Response<DataItem> response) {
                if (response.isSuccessful() == true){
                    String user = response.body().getUsername();
                    Toast.makeText(context, "Berhasil Login", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Gagal Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataItem> call, Throwable t) {
                Toast.makeText(context,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        

    }


    private boolean validateLogin(String username, String password) {
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}