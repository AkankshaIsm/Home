package com.example.hp.home;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.example.hp.home.models.MovieModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

import butterknife.InjectView;
import butterknife.ButterKnife;
/**
 * Created by hp on 10-05-2016.
 */
public class AboutMovie extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {
    private static final String imageURL="https://image.tmdb.org/t/p/w780";
     @InjectView(R.id.movie)TextView movie;
     @InjectView(R.id.overview)TextView overview;
    @InjectView(R.id.frame)FrameLayout frameLayout;
     @InjectView(R.id.myvideo)SurfaceView surfaceView;
    private SurfaceHolder videoHolder;
    private MediaPlayer mediaPlayer;
    private String vidAddress = "https://ia800201.us.archive.org/22/items/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_movie);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        MovieModel movieModel = (MovieModel)intent.getSerializableExtra("MyKey");
        movie.setText(movieModel.getTitle());
        overview.setText(movieModel.getOverview());
        videoHolder = surfaceView.getHolder();
        videoHolder.addCallback(this);
        Uri uri = Uri.parse(imageURL+movieModel.getPosterPath());


       Picasso.with(this).load(uri).into(new Target() {
           @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
           @Override
           public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
               frameLayout.setBackground(new BitmapDrawable(AboutMovie.this.getResources(),bitmap));
           }

           @Override
           public void onBitmapFailed(Drawable errorDrawable) {

           }

           @Override
           public void onPrepareLoad(Drawable placeHolderDrawable) {

           }
       });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(videoHolder);
            mediaPlayer.setDataSource(vidAddress);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
       mediaPlayer.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();

    }
    @Override
    protected void onDestroy()
    {   if(mediaPlayer!= null)
        mediaPlayer.release();

        super.onDestroy();

    }


}

