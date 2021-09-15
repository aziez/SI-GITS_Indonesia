package com.aziz.avplayer.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.aziz.avplayer.R;
import com.chibde.visualizer.SquareBarVisualizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MusicFragment extends Fragment implements View.OnClickListener {

    private ImageButton Play, Pause;
    private Button Stop,next;
    private MediaPlayer mediaPlayer;
    private SquareBarVisualizer visualizer;
    private ListView listView;
    private String[] namaLagu;
    private ArrayList<String> listSong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);


        visualizer = (SquareBarVisualizer)  view.findViewById(R.id.visualizer);
        Play = (ImageButton) view.findViewById(R.id.play);
        Pause = (ImageButton) view.findViewById(R.id.pause);
        Stop = (Button) view.findViewById(R.id.stop);
        next = (Button) view.findViewById(R.id.next);
        listView = (ListView) view.findViewById(R.id.list);
        mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.ungu_sayang);


        namaLagu = new String[]{"Nama Lagu", "Nama Lagu2", "namaLagu 3"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,namaLagu);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                Toast.makeText(view.getContext(), "Lagu di Pilih" + selected, Toast.LENGTH_SHORT).show();
                pauseAudio();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        playAudio();
                    }
                },5000);
            }
        });

        stateAwal();

        Play.setOnClickListener(this);
        Pause.setOnClickListener(this);
        Stop.setOnClickListener(this);
        next.setOnClickListener(this);

        visualizer.setColor(ContextCompat.getColor(this.getContext(), R.color.purple_700));
        visualizer.setDensity(65);
        visualizer.setGap(2);

        return view;
    }


    private void stateAwal() {
        Play.setVisibility(View.VISIBLE);
        Pause.setVisibility(View.GONE);
        Stop.setEnabled(false);
    }

    private void nextList() {
        int allList = namaLagu.length;
        for (int a = 0; a < allList; a++){
            listView.setSelection(a);
        }
    }

    private void playAudio(){

        Play.setVisibility(View.GONE);
        Pause.setVisibility(View.VISIBLE);
        Stop.setEnabled(true);

        try {
            mediaPlayer.prepare();
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        visualizer.setPlayer(mediaPlayer.getAudioSessionId());


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stateAwal();
            }
        });

    }

    public void pauseAudio(){

        if (mediaPlayer.isPlaying()){
            if (mediaPlayer != null){
                mediaPlayer.pause();
            }
        }else{
            if (mediaPlayer != null){
                mediaPlayer.start();
            }
        }

    }

    private void stopAudio(){
        mediaPlayer.stop();

        try {
            mediaPlayer.setDataSource(String.valueOf(R.raw.ungu_sayang));
            mediaPlayer.seekTo(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.prepareAsync();

        stateAwal();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                playAudio();
                break;
            case R.id.pause:
                pauseAudio();
                break;
            case R.id.stop:
                stopAudio();
                break;
            case R.id.next:
                nextList();
                break;
        }

    }
}