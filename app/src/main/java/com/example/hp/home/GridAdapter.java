package com.example.hp.home;

/**
 * Created by hp on 12-05-2016.
 */
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.home.R;
import com.example.hp.home.models.MovieModel;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;
import butterknife.ButterKnife;
import java.util.List;

/**
 * Created by hp on 12-05-2016.
 */
public class GridAdapter  extends BaseAdapter{
    private List<MovieModel> movieModelList;
    private Context context;
    private String imageURL;
    private static LayoutInflater inflater = null;
    @InjectView(R.id.movie)TextView movie;
    @InjectView(R.id.year)TextView year;
    @InjectView(R.id.rating)TextView rating;
    @InjectView(R.id.image)ImageView image;

    public GridAdapter(List<MovieModel> movieModelList,Context context)
    {
        this.movieModelList = movieModelList;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //this.imageURL = imageURL;
    }
    @Override
    public int getCount() {
        return movieModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        MovieModel movieModel = movieModelList.get(position);
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.row, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.movie.setText(movieModel.getTitle());
        holder.year.setText(movieModel.getReleaseDate());
        holder.rating.setText(movieModel.getVoteAverage().toString());
        Uri uri = Uri.parse(imageURL+movieModel.getPosterPath());
        Context context = holder.image.getContext();
        Picasso.with(context).load(uri).into(holder.image);
        return convertView;

    }

    static final class ViewHolder {
        @InjectView(R.id.movie) TextView movie;
        @InjectView(R.id.year) TextView year;
        @InjectView(R.id.rating) TextView rating;
        @InjectView(R.id.imageView)ImageView image;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
