package com.aziz.todoit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private Boolean isFirst;
    private Boolean isLogin;
    private String usname,uspas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences("KUNCI", MODE_PRIVATE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (sharedPreferences.contains("INTRO")){
                        Intent board = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(board);
                        finish();
                    }if (sharedPreferences.contains("LOGIN")){
                        Intent main = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(main);
                        finish();
                    }else{
                        Intent board = new Intent(SplashActivity.this, OnBoardingActivity.class);
                        startActivity(board);
                        finish();
                    }
                }
            }, 1000);
    }
}