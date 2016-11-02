package com.android.java.miss.retrofithttplibrary.rest;

import com.android.java.miss.retrofithttplibrary.model.Movie;
import com.android.java.miss.retrofithttplibrary.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
  @GET("movie/top_rated")
  Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

  @GET("movie/{id}")
  Call<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
