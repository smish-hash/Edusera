package com.example.edusera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.edusera.Model.TopicModel;
import com.example.edusera.Utils.YoutubeConfig;
import com.example.edusera.databinding.ActivityPlayerBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeBaseActivity {

    private ActivityPlayerBinding binding;
    private YouTubePlayerView playerView;
    private String param;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private TopicModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        model = (TopicModel) intent.getSerializableExtra("videoData");

        String link = model.getVideoLink();

        playerView = binding.player;

        binding.tvTitle.setText(model.getTitle());

        if (link != null) {
            Uri uri = Uri.parse(link);
            param = uri.getQueryParameter("v");
        }

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (param != null)
                    youTubePlayer.loadVideo(param);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        playerView.initialize(YoutubeConfig.getApiKey(), onInitializedListener);



    }
}