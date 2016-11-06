package com.android.java.miss.retrofithttplibrary.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.java.miss.retrofithttplibrary.R;
import com.android.java.miss.retrofithttplibrary.adapter.MoviesAdapter;
import com.android.java.miss.retrofithttplibrary.model.Movie;
import com.android.java.miss.retrofithttplibrary.model.MovieResponse;
import com.android.java.miss.retrofithttplibrary.rest.ApiClient;
import com.android.java.miss.retrofithttplibrary.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.android.java.miss.retrofithttplibrary.activity.MainActivity.API_KEY;

/**
 * Created by suadahaji.
 */

public class TopRatedMoviesFragment extends Fragment {
  public static boolean isTwoPane = false;

  public TopRatedMoviesFragment() {

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.top_rated_movies_fragment, container, false);
    final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.movies_recycler_view);
    recyclerView.setHasFixedSize(true);

    if (view.findViewById(R.id.detailContainer) != null) {
      isTwoPane = true;
      recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
    } else {
      recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }


    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
    call.enqueue(new Callback<MovieResponse>() {
      @Override
      public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
        ArrayList<Movie> movies = response.body().getResults();
        recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getContext()));
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t) {
        Log.e(TAG, t.toString());
      }
    });
    return view;
  }
}
