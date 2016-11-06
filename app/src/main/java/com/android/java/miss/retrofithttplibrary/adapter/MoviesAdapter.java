package com.android.java.miss.retrofithttplibrary.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.java.miss.retrofithttplibrary.R;
import com.android.java.miss.retrofithttplibrary.activity.DetailActivity;
import com.android.java.miss.retrofithttplibrary.fragments.DetailFragment;
import com.android.java.miss.retrofithttplibrary.fragments.TopRatedMoviesFragment;
import com.android.java.miss.retrofithttplibrary.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.android.java.miss.retrofithttplibrary.activity.MainActivity.MOVIE_BUNDLE;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

  private ArrayList<Movie> movies;
  private int rowLayout;
  private static Context context;
  private static final String TAG = MoviesAdapter.class.getSimpleName();

  public static class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView moviePoster;
    TextView movieTitle;
    TextView date;
    TextView rating;
    String movie_id;

    public MovieViewHolder(View v) {
      super(v);

      if (!TopRatedMoviesFragment.isTwoPane) {
        moviePoster = (ImageView) v.findViewById(R.id.iv_image);
        movieTitle = (TextView) v.findViewById(R.id.movie_title);
        date = (TextView) v.findViewById(R.id.movie_date);
        rating = (TextView) v.findViewById(R.id.rating);
      } else {
        moviePoster = (ImageView) v.findViewById(R.id.iv_image);
      }
      movie_id = null;

      v.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          if (TopRatedMoviesFragment.isTwoPane) {
            openTwoPane(movie_id);
             }  else {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(MOVIE_BUNDLE, movie_id);
            context.startActivity(intent);
          }
        }
      });
    }

    public void openTwoPane(String id_movie) {
      Bundle bundle = new Bundle();
      bundle.putString(DetailActivity.MOVIE_ID, id_movie);
      DetailFragment fragment = new DetailFragment();
      fragment.setArguments(bundle);
      ((Activity)context).getFragmentManager().beginTransaction().replace(R.id.detailContainer,fragment).commit();
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
    holder.movie_id = String.valueOf(movies.get(position).getId());

    if (!TopRatedMoviesFragment.isTwoPane) {
      holder.movieTitle.setText(movies.get(position).getTitle());
      holder.date.setText(movies.get(position).getReleaseDate());
      holder.rating.setText(Double.toString(movies.get(position).getVoteAverage()));
      Picasso.with(context).load("http://image.tmdb.org/t/p/original/" + movies.get(position).getPosterPath()).into(holder.moviePoster);
    } else {
      Picasso.with(context).load("http://image.tmdb.org/t/p/original/" + movies.get(position).getPosterPath()).into(holder.moviePoster);
    }


  }

  @Override
  public int getItemCount() {
    return movies.size();
  }
}
