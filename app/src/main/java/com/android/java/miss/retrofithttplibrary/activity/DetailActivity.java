package com.android.java.miss.retrofithttplibrary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.java.miss.retrofithttplibrary.R;
import com.android.java.miss.retrofithttplibrary.model.Movie;
import com.android.java.miss.retrofithttplibrary.rest.ApiClient;
import com.android.java.miss.retrofithttplibrary.rest.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
  private TextView movieTitle;
  private ImageView movieImage;
  private TextView movieRating;
  private TextView releaseDate;
  private TextView overview;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    getSupportActionBar().setTitle("Detail Activity");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    movieTitle = (TextView) findViewById(R.id.movie_title);
    movieImage = (ImageView) findViewById(R.id.movie_image);
    movieRating = (TextView) findViewById(R.id.rating);
    releaseDate = (TextView) findViewById(R.id.release_date);
    overview = (TextView) findViewById(R.id.overview);


   String id = getIntent().getStringExtra(MainActivity.MOVIE_BUNDLE);
    int movieId = Integer.parseInt(id);
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    Call<Movie> call = apiService.getMovieDetails(movieId, MainActivity.API_KEY);
    call.enqueue(new Callback<Movie>() {
      @Override
      public void onResponse(Call<Movie> call, Response<Movie> response) {
        movieTitle.setText(response.body().getTitle());
        Picasso.with(DetailActivity.this).load("http://image.tmdb.org/t/p/original/" + response.body().getPosterPath()).into(movieImage);
        movieRating.setText(Double.toString(response.body().getVoteAverage()));
        releaseDate.setText(response.body().getReleaseDate());
        overview.setText(response.body().getOverview());

      }

      @Override
      public void onFailure(Call<Movie> call, Throwable t) {

      }
    });

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
    }
    return true;
  }
}
