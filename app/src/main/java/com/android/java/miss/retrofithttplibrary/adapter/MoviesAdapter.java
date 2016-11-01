package com.android.java.miss.retrofithttplibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.java.miss.retrofithttplibrary.R;
import com.android.java.miss.retrofithttplibrary.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

  private List<Movie> movies;
  private int rowLayout;
  private Context context;

  public static class MovieViewHolder extends RecyclerView.ViewHolder {
    RelativeLayout moviesLayout;
    ImageView moviePoster;
    TextView movieTitle;
    TextView date;
    TextView rating;


    public MovieViewHolder(View v) {
      super(v);
      moviesLayout = (RelativeLayout) v.findViewById(R.id.movies_layout);
      moviePoster = (ImageView) v.findViewById(R.id.iv_image);
      movieTitle = (TextView) v.findViewById(R.id.movie_title);
      date = (TextView) v.findViewById(R.id.movie_date);
      rating = (TextView) v.findViewById(R.id.rating);
    }
  }


  public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
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
    Picasso.with(context).load("http://image.tmdb.org/t/p/original/" + movies.get(position).getBackdropPath()).into(holder.moviePoster);
    holder.rating.setText(movies.get(position).getVoteAverage().toString());
  }
  @Override
  public int getItemCount() {
    return movies.size();
  }
}
