package com.example.hp.home;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.hp.home.models.MovieModel;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.InjectView;
/**
 * Created by hp on 07-05-2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<MovieModel> movieModelList;
    private String imageURL;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.movie)TextView movie;
        @InjectView(R.id.year)TextView year;
        @InjectView(R.id.rating)TextView rating;
        @InjectView(R.id.image)ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);

        }
    }
    public MyAdapter(List<MovieModel> movieModelList,String imageURL)
    {
      this.movieModelList=movieModelList;
      this.imageURL=imageURL;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        MovieModel movieModel=movieModelList.get(position);
        holder.movie.setText(movieModel.getTitle());
        holder.year.setText(movieModel.getReleaseDate());
        holder.rating.setText(movieModel.getVoteAverage().toString());
        Uri uri = Uri.parse(imageURL+movieModel.getPosterPath());
        Context context = holder.image.getContext();
        Picasso.with(context).load(uri).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }  //returns 0 if no item is fetched
}
