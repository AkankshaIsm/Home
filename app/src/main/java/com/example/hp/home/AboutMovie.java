package com.example.hp.home;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.hp.home.models.MovieModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import butterknife.InjectView;
import butterknife.ButterKnife;
/**
 * Created by hp on 10-05-2016.
 */
public class AboutMovie extends AppCompatActivity {
    private static final String imageURL="https://image.tmdb.org/t/p/w780";
     @InjectView(R.id.movie)TextView movie;
     @InjectView(R.id.overview)TextView overview;
    @InjectView(R.id.frame)FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_movie);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        MovieModel movieModel = (MovieModel)intent.getSerializableExtra("MyKey");
        movie.setText(movieModel.getTitle());
        overview.setText(movieModel.getOverview());
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
}
