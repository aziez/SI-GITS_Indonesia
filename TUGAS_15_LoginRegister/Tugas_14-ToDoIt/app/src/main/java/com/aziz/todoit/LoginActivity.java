package com.aziz.todoit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aziz.todoit.model.user.DataItem;
import com.aziz.todoit.model.user.UserModel;
import com.aziz.todoit.rest.ApiConfig;
import com.aziz.todoit.rest.ApiService;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private SharedPreferences sharedPreferences;
    private Boolean isLogin,isIntro;
    
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
            ApiService apiService = ApiConfig.getApiService();

            apiService.Postlogin(txusername.getText().toString(), txpass.getText().toString())
                    .enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                            isLogin = response.body().isSucsess();

                            if (isLogin){
                                sharedPreferences = getSharedPreferences("KUNCI", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("LOGIN", isLogin);
                                editor.apply();

                                Toast.makeText(context.getApplicationContext(), "Berhasil Login "+response.body().isSucsess(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(context.getApplicationContext(), MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(context.getApplicationContext(), "Email Password Salah", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {
                            Log.d(TAG, "onFailure: GAGAL DATABASES "+t.getMessage());
                        }
                    });
        }


    }


    private boolean validateLogin(String username, String password) {
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            btnLogin.setEnabled(false);
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            btnLogin.setEnabled(false);
            return false;
        }
        return true;

    }
}