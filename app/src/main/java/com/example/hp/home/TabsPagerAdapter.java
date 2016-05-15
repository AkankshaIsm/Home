package com.example.hp.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.example.hp.home.models.Example;
import com.example.hp.home.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hp on 12-05-2016.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private List<MovieModel> movieModelList;
    private String KEY = "MYKEY";
    private String tabTitles[] = {"Now Playing","Popular","Upcoming"};


    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    public Fragment getItem(int position) {
        Log.e("sunnnnnn","tabspager m hu ");

        return PageFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabTitles[position];
    }


}
