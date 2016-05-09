package com.example.hp.home;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.hp.home.models.MovieModel;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by hp on 07-05-2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<MovieModel> movieModelList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
     public TextView year,rating,movie;
        public MyViewHolder(View itemView) {
            super(itemView);
            movie=(TextView)itemView.findViewById(R.id.movie);
            year=(TextView)itemView.findViewById(R.id.year);

        }
    }
    public MyAdapter(List<MovieModel> movieModelList)
    {
    this.movieModelList=movieModelList;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
   MovieModel movieModel=movieModelList.get(position);
       holder.movie.setText(movieModel.getMovie());
        holder.year.setText(movieModel.getYear());

    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }
}
