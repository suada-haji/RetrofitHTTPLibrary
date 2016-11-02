package com.android.java.miss.retrofithttplibrary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.java.miss.retrofithttplibrary.rest.ApiInterface;
import com.android.java.miss.retrofithttplibrary.R;
import com.android.java.miss.retrofithttplibrary.adapter.MoviesAdapter;
import com.android.java.miss.retrofithttplibrary.model.Movie;
import com.android.java.miss.retrofithttplibrary.model.MovieResponse;
import com.android.java.miss.retrofithttplibrary.rest.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity{
  private static final String TAG = MainActivity.class.getSimpleName();
  public final static String API_KEY = "5ab68c282365772dba538bd0db9f5fda";

  public static final String MOVIE_BUNDLE = "MOVIE_BUNDLE";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    if (API_KEY.isEmpty()) {
      Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG);
      return;
    }
    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
    call.enqueue(new Callback<MovieResponse>() {
      @Override
      public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
        ArrayList<Movie> movies = response.body().getResults();
        recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t) {
        Log.e(TAG, t.toString());
      }
    });
  }

}
