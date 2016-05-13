package com.example.hp.home;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.hp.home.models.Example;
import com.example.hp.home.models.MovieModel;

import java.util.Collections;
import java.util.List;

import butterknife.InjectView;
import butterknife.ButterKnife;
import retrofit.Callback;
;

/**
 * Created by hp on 12-05-2016.
 */
public class SwipeActivity extends AppCompatActivity{
    @InjectView(R.id.pager)ViewPager viewPager;
    @InjectView(R.id.tabstrip)PagerSlidingTabStrip pagerSlidingTabStrip;
    public static String rootURL="http://api.themoviedb.org/3/movie/now_playing"; //base url for movies
    private static final String imageURL="https://image.tmdb.org/t/p/w780";
    private TabsPagerAdapter adapter;
    private List<MovieModel> movieModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe);
        ButterKnife.inject(this);
        adapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0:
//                        rootURL = "http://api.themoviedb.org/3/movie/now_playing";
//                        PageFragment.newInstance(position,getMovies());
//                    case 1:
//                        rootURL = "http://api.themoviedb.org/3/movie/popular";
//                        PageFragment.newInstance(position,getMovies());
//                    case 2:
//                        rootURL = "http://api.themoviedb.org/3/movie/upcoming";
//                        PageFragment.newInstance(position,getMovies());
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

//    private List<MovieModel> getMovies()
//    {
//        //While the app fetched data we are displaying a progress dialog
//        final ProgressDialog loading = new ProgressDialog(SwipeActivity.this,R.style.AppTheme_AppBarOverlay);
//        loading.setIndeterminate(true);
//        loading.setMessage("Fetching Data");
//        loading.show();
//
//        //Creating a rest adapter
//        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(rootURL).build();
//        MovieAPI api=adapter.create(MovieAPI.class);
//        api.getMovies(new Callback<Example>() {
//
//            @Override
//            public void success(Example movieModels, Response response)
//            {
//                loading.dismiss();  //if successful in fetching the json, stop the progress dialog
//                movieModelList=movieModels.getResults(); //get movie model object
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                //unable to fetch json from server
//                loading.dismiss();
//                Toast.makeText(SwipeActivity.this, "Unable to fetch data", Toast.LENGTH_SHORT).show();
//                Log.e("error retro", error.toString());
//            }
//        });
//        return movieModelList;
//
//    }
//
}