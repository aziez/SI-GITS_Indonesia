package com.aziz.todoit.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.aziz.todoit.R;

public class BoardingAdapter extends PagerAdapter {
    private static final int MAX_STEP = 4;
    Context context;

    private Button btn_go;
    private final String[] title_array = {
            "Tugas Ke-15",
            "Todo APP Keren",
            "One Login register",
            "Database Integrated"
    };

    private final String[] desc_array = {
            "Aplikasi Tugas ke-15 Studi Independent PT.Gits Indonesia",
            "Aplikasi todo Keren dengan buanyak Feature",
            "Sekali Login dapat masuk selamanya kecuali di logout",
            "Terintegrasi dengan SQL database dengan BAckend Express.js"
    };

    private final int[] img_array = {
            R.drawable.ic_baseline_supervised_user_circle_24,
            R.drawable.ic_baseline_today_24,
            R.drawable.ic_baseline_lock_24,
            R.drawable.ic_baseline_view_list_24
    };


    public BoardingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View Boardinglayout = layoutInflater.inflate(R.layout.intro_item, null);

        ImageView imgSlide = Boardinglayout.findViewById(R.id.imageSlider);
        TextView tvTitle = Boardinglayout.findViewById(R.id.title);
        TextView tvDesc = Boardinglayout.findViewById(R.id.description);

        imgSlide.setImageResource(img_array[position]);
        tvTitle.setText(title_array[position]);
        tvDesc.setText(desc_array[position]);


        container.addView(Boardinglayout);
        return  Boardinglayout;
    }

    @Override
    public int getCount() {
        return desc_array.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
