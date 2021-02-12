package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    //context used to inflate a view, where adapter is constructed from
    Context context;

    //the data
    List<Movie> movies;

    double votingAverageVal;

    //private final int NOT_POPULAR = 0, POPULAR = 1;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating (choosing a row layout) a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        String tag = "MovieAdapter";
        Log.d(tag,"onCreateViewHolder");


        //working code
        View movieView;


        movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);


        return new ViewHolder(movieView);
    }

    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String tag = " MovieAdapter";
        Log.d(tag, "onBindViewHolder" + position);
        //Get the movie at the position selected
        Movie movie = movies.get((position));
        //Bind movie data into view holder
        holder.bind(movie);
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//        //More to come
//        if(movies.get(position).getVoteAverage() < 5 )
//            return NOT_POPULAR;
//        else if(movies.get(position).getVoteAverage() > 5 )
//            return POPULAR;
//        return -1;
//    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);

        }

        public void bind(Movie movie) {
            //Stretch feature for if a movie has a rating of greater than 5

             votingAverageVal = movie.getVoteAverage();


                    tvTitle.setText((movie.getTitle()));
                    tvOverview.setText(movie.getOverview());


                    String imageUrl;
                    //if phone in landscape, set image as backdrop img, else poster path

                    if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        imageUrl = movie.getBackdropPath();
                    } else
                        imageUrl = movie.getPosterPath();

                    Glide.with(context).load(imageUrl).into(ivPoster);
                }


            }

}

