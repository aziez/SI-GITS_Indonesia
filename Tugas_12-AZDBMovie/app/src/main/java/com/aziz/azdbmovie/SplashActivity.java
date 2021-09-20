package com.aziz.azdbmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkInternet();

    }

    private void checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getActiveNetworkInfo() != null){
            Toast.makeText(this, "Internet Terhubung", Toast.LENGTH_SHORT).show();
            initMain();
        }else{
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Internet Tidak terhubung!")
                    .setConfirmText("Reload")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            finish();
                            startActivity(getIntent());
                        }
                    })
                    .show();
        }
    }


    private void initMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }, 4000);
    }
}