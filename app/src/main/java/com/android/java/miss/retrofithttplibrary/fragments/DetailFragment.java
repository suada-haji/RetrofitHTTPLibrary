package com.android.java.miss.retrofithttplibrary.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.java.miss.retrofithttplibrary.R;
import com.android.java.miss.retrofithttplibrary.activity.DetailActivity;
import com.android.java.miss.retrofithttplibrary.activity.MainActivity;
import com.android.java.miss.retrofithttplibrary.adapter.CastAdapter;
import com.android.java.miss.retrofithttplibrary.model.CastResponse;
import com.android.java.miss.retrofithttplibrary.model.Movie;
import com.android.java.miss.retrofithttplibrary.model.MovieCasts;
import com.android.java.miss.retrofithttplibrary.rest.ApiClient;
import com.android.java.miss.retrofithttplibrary.rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFragment extends Fragment{

  private TextView movieTitle;
  private ImageView movieImage;
  private TextView movieRating;
  private TextView releaseDate;
  private TextView overview;
  private String id;

  public DetailFragment() {

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Bundle bundle =getArguments();
    if (bundle != null) {
      id = bundle.getString(DetailActivity.MOVIE_ID);
    }
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.activity_detail, container, false);
    final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.casts_recycler_view);

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

    movieTitle = (TextView) view.findViewById(R.id.movie_title);
    movieImage = (ImageView) view.findViewById(R.id.movie_image);
    movieRating = (TextView) view.findViewById(R.id.rating);
    releaseDate = (TextView) view.findViewById(R.id.release_date);
    overview = (TextView) view.findViewById(R.id.overview);

    int movieId = Integer.parseInt(id);
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    Call<Movie> call = apiService.getMovieDetails(movieId, MainActivity.API_KEY);
    Call<CastResponse> castsCall = apiService.getMovieCasts(movieId, MainActivity.API_KEY);
    call.enqueue(new Callback<Movie>() {
      @Override
      public void onResponse(Call<Movie> call, Response<Movie> response) {

        movieTitle.setText(response.body().getTitle());
        movieRating.setText(Double.toString(response.body().getVoteAverage()));
        releaseDate.setText(response.body().getReleaseDate());
        overview.setText(response.body().getOverview());
        if (TopRatedMoviesFragment.isTwoPane) {
          Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original/" + response.body().getBackdropPath()).into(movieImage);
        } else {
          Picasso.with(getContext()).load("http://image.tmdb.org/t/p/original/" + response.body().getPosterPath()).into(movieImage);
        }
      }

      @Override
      public void onFailure(Call<Movie> call, Throwable t) {

      }
    });

    castsCall.enqueue(new Callback<CastResponse>() {
      @Override
      public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
        ArrayList<MovieCasts> movieCasts = response.body().getCasts();
        recyclerView.setAdapter(new CastAdapter(movieCasts, R.layout.list_item_cast, getContext()));
       // Log.d("Casts", "Size" + movieCasts.size());
      }

      @Override
      public void onFailure(Call<CastResponse> call, Throwable t) {

      }
    });

    return view;
  }
}