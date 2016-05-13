package com.example.hp.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hp on 12-05-2016.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = {"Now Playing","Popular","Upcoming"};
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
//         switch (position) {
//             case 0 :return PageFragment.newInstance("https://image.tmdb.org/t/p/w780/now_playing");
//             case 1 :return PageFragment.newInstance("https://image.tmdb.org/t/p/w780/popular");
//             case 2 :return PageFragment.newInstance("https://image.tmdb.org/t/p/w780/upcoming");
//         }
//        return PageFragment.newInstance("https://image.tmdb.org/t/p/w780/now_playing");
        return null;
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
