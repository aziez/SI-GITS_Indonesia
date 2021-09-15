package com.aziz.avplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.aziz.avplayer.fragment.MusicFragment;
import com.aziz.avplayer.fragment.VideoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = (TabLayout) findViewById(R.id.tabAtas);
        viewPager = (ViewPager) findViewById(R.id.pager);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setupViewPager();

        tab.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupViewPager() {
       SectionpagerAdapter adapter = new SectionpagerAdapter(getSupportFragmentManager());
       adapter.addFragment(new MusicFragment(), "music");
       adapter.addFragment(new VideoFragment(), "video");
       viewPager.setAdapter(adapter);


    }

    private class SectionpagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> titleList = new ArrayList<>();


        public SectionpagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            titleList.add(title);
        }

        public CharSequence getPagetitle(int pos){
            return  titleList.get(pos);
        }
    }

    private void setupTabIcons() {
        tab.getTabAt(0).setIcon(R.drawable.ic_baseline_library_music_24).setText("music");
        tab.getTabAt(1).setIcon(R.drawable.ic_baseline_video_library_24).setText("video");


    }

}