package com.aziz.todoit;

import android.annotation.SuppressLint;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aziz.todoit.adapter.BoardingAdapter;
import com.aziz.todoit.adapter.TaskAdapter;
import com.aziz.todoit.databinding.ActivityOnBoardingBinding;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageButton btnNext;
    private TextView txtSkip,txtFinish;
    private Integer curtPost = 0;
    private SharedPreferences sharedPreferences;
    private Boolean isFirst = true;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_on_boarding);


            viewPager = (ViewPager) findViewById(R.id.viewPager);
            btnNext = (ImageButton) findViewById(R.id.btn_next_step);
            txtSkip = (TextView) findViewById(R.id.text_skip);
            txtFinish = (TextView) findViewById(R.id.text_end);

            sharedPreferences = getSharedPreferences("KUNCI", MODE_PRIVATE);
            isFirst = sharedPreferences.getBoolean("INTRO", true);



            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences = getSharedPreferences("KUNCI", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("INTRO", true);
                    editor.apply();
                    Next();
                }
            });

            txtSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences = getSharedPreferences("KUNCI", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("INTRO", true);
                    editor.apply();
                    Skip();
                }
            });

            txtFinish.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Skip();
                        }
                    }
            );

            BoardingAdapter boardingAdapter = new BoardingAdapter(this);
            viewPager.setAdapter(boardingAdapter);

        }

        public void Skip(){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        public void Next(){
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            ++curtPost;

            if (viewPager.getCurrentItem() == 3){
                Toast.makeText(getApplicationContext(), "Terahir", Toast.LENGTH_SHORT).show();
                btnNext.setVisibility(View.GONE);
                txtFinish.setVisibility(View.VISIBLE);

            }
        }


    }