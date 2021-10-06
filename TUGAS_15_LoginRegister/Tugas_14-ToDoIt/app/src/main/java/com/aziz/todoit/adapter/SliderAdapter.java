package com.aziz.todoit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.aziz.todoit.R;
import com.aziz.todoit.model.task.DataItem;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class SliderAdapter extends PagerAdapter {
     Context context;
     List<DataItem> dataItems;

    public SliderAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout = layoutInflater.inflate(R.layout.slide_hero, null);

        TextView textView = sliderLayout.findViewById(R.id.slide_task);
        textView.setMaxLines(3);
        ImageView img = sliderLayout.findViewById(R.id.imgslide);

        textView.setText(dataItems.get(position).getTaskName());
        if (dataItems.get(position).getStatus().equalsIgnoreCase("peding")){
            img.setImageResource(R.drawable.ic_baseline_timer_24);
        }else if (dataItems.get(position).getStatus().equalsIgnoreCase("complete")){
            img.setImageResource(R.drawable.ic_baseline_done_outline_24);
        }



        container.addView(sliderLayout);
        return sliderLayout;
    }

    @Override
    public int getCount() {
        return dataItems.size();
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
