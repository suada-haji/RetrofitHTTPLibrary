package com.android.java.miss.retrofithttplibrary.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.java.miss.retrofithttplibrary.R;
import com.android.java.miss.retrofithttplibrary.activity.DetailActivity;
import com.android.java.miss.retrofithttplibrary.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.android.java.miss.retrofithttplibrary.activity.MainActivity.MOVIE_BUNDLE;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

  private ArrayList<Movie> movies;
  private int rowLayout;
  private static Context context;

  public static class MovieViewHolder extends RecyclerView.ViewHolder {
    RelativeLayout moviesLayout;
    ImageView moviePoster;
    TextView movieTitle;
    TextView date;
    TextView rating;
    String movie_id;

    public MovieViewHolder(View v) {
      super(v);
      moviesLayout = (RelativeLayout) v.findViewById(R.id.movies_layout);
      moviePoster = (ImageView) v.findViewById(R.id.iv_image);
      movieTitle = (TextView) v.findViewById(R.id.movie_title);
      date = (TextView) v.findViewById(R.id.movie_date);
      rating = (TextView) v.findViewById(R.id.rating);
      movie_id = null;

      v.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent = new Intent(context, DetailActivity.class);
          intent.putExtra(MOVIE_BUNDLE, movie_id);
          context.startActivity(intent);
        }
      });
    }
  }

  public MoviesAdapter(ArrayList<Movie> movies, int rowLayout, Context context) {
    this.movies = movies;
    this.rowLayout = rowLayout;
    this.context = context;
  }

  @Override
  public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MovieViewHolder holder, final int position) {
    holder.movieTitle.setText(movies.get(position).getTitle());
    holder.date.setText(movies.get(position).getReleaseDate());
    Picasso.with(context).load("http://image.tmdb.org/t/p/original/" + movies.get(position).getPosterPath()).into(holder.moviePoster);
    holder.rating.setText(Double.toString(movies.get(position).getVoteAverage()));
    holder.movie_id = String.valueOf(movies.get(position).getId());
  }

  @Override
  public int getItemCount() {
    return movies.size();
  }

}
