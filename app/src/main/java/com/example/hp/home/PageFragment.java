package com.example.hp.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.home.models.Example;
import com.example.hp.home.models.MovieModel;

import butterknife.InjectView;
import butterknife.ButterKnife;
import retrofit.Callback;
;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by hp on 12-05-2016.
 */
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    @InjectView(R.id.gridlayout)GridView gridView;
    private GridAdapter gridAdapter = null;
    private List<MovieModel> movieList;
    private int mpage;
    public static PageFragment newInstance(int page,List<MovieModel> movieModelList)
    {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
       // args.putSerializable(movieModelList);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mpage = getArguments().getInt(ARG_PAGE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.sample,container,false);
        ButterKnife.inject(gridView);
        //gridAdapter = new GridAdapter()

        return view;
    }


}
