package com.aziz.azdbmovie.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.aziz.azdbmovie.DetailActivity;
import com.aziz.azdbmovie.R;
import com.aziz.azdbmovie.model.ResultsItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private List<ResultsItem> mlist;

    public SliderAdapter(Context context, List<ResultsItem> mlist) {
        this.context = context;
        this.mlist = mlist;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = layoutInflater.inflate(R.layout.slide_item, null);

        ImageView imgSlide = slideLayout.findViewById(R.id.slide_img);
        TextView txtSlide = slideLayout.findViewById(R.id.slide_title);


        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+mlist.get(position).getBackdropPath())
                .into(imgSlide);

        txtSlide.setText(mlist.get(position).getTitle());
        container.addView(slideLayout);

        slideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("title", mlist.get(position).getTitle());
                intent.putExtra("imgUrl", mlist.get(position).getPosterPath());
                intent.putExtra("imgCover", mlist.get(position).getBackdropPath());
                intent.putExtra("dec", mlist.get(position).getOverview());
                intent.putExtra("rating", mlist.get(position).getVoteAverage());

                v.getContext().startActivity(intent);

                Toast.makeText(v.getContext(), "Anda Memilih " + mlist.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return slideLayout;


    }

    @Override
    public int getCount() {
        return mlist.size();
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
