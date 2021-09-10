package com.aziz.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleButton;
    private RelativeLayout rtregis,rtLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rtregis = findViewById(R.id.regis);
        rtLogin = findViewById(R.id.login);

        toggleButton = findViewById(R.id.togleInclude);
        
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                     rtLogin.setVisibility(View.GONE);
                     rtregis.setVisibility(View.VISIBLE);


                }else{
                    rtregis.setVisibility(View.GONE);
                    rtLogin.setVisibility(View.VISIBLE);
                }
            }
        });

    }

}
