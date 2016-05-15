package com.example.hp.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.home.models.Example;
import com.example.hp.home.models.MovieModel;

import butterknife.InjectView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hp on 12-05-2016.
 */
public class PageFragment extends Fragment{
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_OBJECT = "ARG_OBJECT";
    public static final String rootURL="http://api.themoviedb.org/3/movie/";

   // @InjectView(R.id.gridlayout)GridView gridView;
   //base url for movies  always end with /
    private static final String imageURL="https://image.tmdb.org/t/p/w780"; //base url for for images
    @InjectView(R.id.recyclerView)RecyclerView recyclerView;
    @InjectView(R.id.spinner)Spinner spinner;
    public static List<MovieModel> movieModelList = null;


    private int mpage;
    public static PageFragment newInstance(int page)
    {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        //args.putSerializable(ARG_OBJECT,example);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);

        mpage = getArguments().getInt(ARG_PAGE);
        switch (mpage)
        {
            case 0: getNowPlaying(); break;
            case 1: getPopular(); break;
            case 2: getUpcoming(); break;
            default: getPopular();
        }



    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.sample,container,false);
        ButterKnife.inject(this, view);


        return view;
    }

    private void showList(List<MovieModel> movieList)
    {
        MyAdapter myAdapter=new MyAdapter(movieList,imageURL);
        ///Log.e("showlist","model null");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        if(mLayoutManager==null)
        {Log.e("showlist", "Layout manager is null");
            return;}

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(myAdapter);
    }

    private void getPopular()
    {
        //While the app fetched data we are displaying a progress dialog
//        final ProgressDialog loading = new ProgressDialog(SwipeActivity,R.style.AppTheme_AppBarOverlay);
//        loading.setIndeterminate(true);
//        loading.setMessage("Fetching Data");
//        loading.show();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(rootURL)
                .build();
        MovieAPI api=adapter.create(MovieAPI.class);
        api.getPopular(new Callback<Example>() {

            @Override
            public void success(Example movieModels, Response response) {
                //loading.dismiss();
                 Log.e("In popular","success");
                movieModelList = movieModels.getResults();
                  if(movieModelList == null)
                  Log.e("In Popular","null hath aaya");//if successful in fetching the json, stop the progress dialog
                //get movie model object
//                switch (pos) {
//                    case 0:
//                        Collections.sort(movieModelList, MovieModel.titleComparator);
//                        break;
//                    case 1:
//                        Collections.sort(movieModelList, MovieModel.ratingComparator);
//                        break;
//                    case 2:
//                        Collections.sort(movieModelList, MovieModel.dateComparator);
//                        break;
//
//                }
                showList(movieModelList);

            }

            @Override
            public void failure(RetrofitError error) {
                //unable to fetch json from server
                ///loading.dismiss();
             //Toast.makeText(SwipeActivity.this, "Unable to fetch data", Toast.LENGTH_SHORT).show();
                Log.e("error retro",error.toString());
            }
        });
    }

    private void getNowPlaying()
    {
        //While the app fetched data we are displaying a progress dialog
//        final ProgressDialog loading = new ProgressDialog(SwipeActivity.this,R.style.AppTheme_AppBarOverlay);
//        loading.setIndeterminate(true);
//        loading.setMessage("Fetching Data");
//        loading.show();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(rootURL)
                .build();
        Log.e("helloooooo","In get now play");
        MovieAPI api=adapter.create(MovieAPI.class);
        api.getNowPlaying(new Callback<Example>() {

            @Override
            public void success(Example movieModels, Response response) {
                //loading.dismiss();
                Log.e("error...pop", "nop");
                movieModelList = movieModels.getResults();   //if successful in fetching the json, stop the progress dialog
                //get movie model object
//                switch (pos) {
//                    case 0:
//                        Collections.sort(movieModelList, MovieModel.titleComparator);
//                        break;
//                    case 1:
//                        Collections.sort(movieModelList, MovieModel.ratingComparator);
//                        break;
//                    case 2:
//                        Collections.sort(movieModelList, MovieModel.dateComparator);
//                        break;
//
//                }
                showList(movieModelList);
            }

            @Override
            public void failure(RetrofitError error) {
                //unable to fetch json from server
                // loading.dismiss();
                //Toast.makeText(SwipeActivity.this, "Unable to fetch data", Toast.LENGTH_SHORT).show();
                Log.e("error retro", error.toString());
            }
        });
    }

    private void getUpcoming()
    {
        //While the app fetched data we are displaying a progress dialog
//        final ProgressDialog loading = new ProgressDialog(SwipeActivity.this,R.style.AppTheme_AppBarOverlay);
//        loading.setIndeterminate(true);
//        loading.setMessage("Fetching Data");
//        loading.show();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(rootURL)
                .build();
        MovieAPI api=adapter.create(MovieAPI.class);
        api.getUpcoming(new Callback<Example>() {

            @Override
            public void success(Example movieModels, Response response) {
                //  loading.dismiss();
                Log.e("error...pop", "up");
                movieModelList = movieModels.getResults();   //if successful in fetching the json, stop the progress dialog
                //get movie model object
//                switch (pos) {
//                    case 0:
//                        Collections.sort(movieModelList, MovieModel.titleComparator);
//                        break;
//                    case 1:
//                        Collections.sort(movieModelList, MovieModel.ratingComparator);
//                        break;
//                    case 2:
//                        Collections.sort(movieModelList, MovieModel.dateComparator);
//                        break;
//
//                }

                showList(movieModelList);
            }

            @Override
            public void failure(RetrofitError error) {
                //unable to fetch json from server
                // loading.dismiss();
                // Toast.makeText(SwipeActivity.this, "Unable to fetch data", Toast.LENGTH_SHORT).show();

                Log.e("error retro", error.toString());
            }
        });
    }
}
