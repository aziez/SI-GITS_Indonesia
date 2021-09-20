package com.aziz.azdbmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.aziz.azdbmovie.fragment.FavoritFragment;
import com.aziz.azdbmovie.fragment.HomeFragment;
import com.aziz.azdbmovie.fragment.TvShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMain();

    }

    private void initMain() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomView);
        bottomNavigationView.inflateMenu(R.menu.bottom_menu);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itm = item.getItemId();

                switch (itm){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.fav:
                        fragment = new FavoritFragment();
                        break;
                    case R.id.tv:
                        fragment = new TvShowFragment();
                        break;
                    default:
                        fragment = new HomeFragment();
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment).commit();
                return true;
            }
        });
    }



    @Override
    public void onBackPressed() {
        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Keluar ?")
                .setContentText("bener nih keluar")
                .setCancelText("NO")
                .setConfirmText("YA")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        finish();
                        System.exit(0);
                        MainActivity.super.onBackPressed();
                    }
                }).show();
    }
}