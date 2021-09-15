package com.aziz.avplayer.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.aziz.avplayer.R;


public class VideoFragment extends Fragment {

    private Button play;
    private VideoView viewVideo;
    private MediaController mediaController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        viewVideo = (VideoView) view.findViewById(R.id.video);
        play = (Button) view.findViewById(R.id.play);
        mediaController = new MediaController(this.getContext());


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.nela_karisma_slenco);

                viewVideo.setVideoURI(uri);
                viewVideo.setMediaController(mediaController);
                mediaController.setAnchorView(viewVideo);

                viewVideo.start();
            }
        });

        return view;
    }
}