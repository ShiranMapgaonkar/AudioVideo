package com.example.audiovideo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView myVideoView;
    private Button btnPlay;
    private MediaController mediaController;
    private Button btnplayMusic;
    private Button btnPause;
    private MediaPlayer mediaPlayer;
    private SeekBar volseekbar;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView=findViewById(R.id.myVideoView);
        btnPlay=findViewById(R.id.btnPlay);
        btnplayMusic = findViewById(R.id.btnplayMusic);
        btnPause = findViewById(R.id.btnPause);
        volseekbar = findViewById(R.id.volseekbar);

        btnPlay.setOnClickListener(MainActivity.this);
        btnplayMusic.setOnClickListener(MainActivity.this);
        btnPause.setOnClickListener(MainActivity.this);

        mediaController = new MediaController(MainActivity.this);
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        int maximumvol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentvol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volseekbar.setMax(maximumvol);
        volseekbar.setProgress(currentvol);

        volseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {

                  //  Toast.makeText(MainActivity.this, Integer.toString(progress),Toast.LENGTH_SHORT).show();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, );
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public void onClick(View buttonView) {


        switch (buttonView.getId()) {

            case (R.id.btnPlay) :
                Uri videoUri= Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.myvideo);
                myVideoView.setVideoURI(videoUri);
                myVideoView.setMediaController(mediaController);
                mediaController.setAnchorView(myVideoView);
                myVideoView.start();
                break;

            case (R.id.btnplayMusic):
                mediaPlayer.start();
                break;

            case (R.id.btnPause):
                mediaPlayer.pause();
                break;



        }


    }
}