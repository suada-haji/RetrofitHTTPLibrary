package com.android.java.miss.retrofithttplibrary.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.java.miss.retrofithttplibrary.R;
import com.android.java.miss.retrofithttplibrary.adapter.ViewPagerAdapter;
import com.android.java.miss.retrofithttplibrary.fragments.TopRatedMoviesFragment;


public class MainActivity extends AppCompatActivity{
  private static final String TAG = MainActivity.class.getSimpleName();
  public final static String API_KEY = "5ab68c282365772dba538bd0db9f5fda";

  public static final String MOVIE_BUNDLE = "MOVIE_BUNDLE";

  private GridLayoutManager layoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Movies");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    if (API_KEY.isEmpty()) {
      Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG);
      return;
    }

    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    setupViewPager(viewPager);

    TabLayout tablayout = (TabLayout) findViewById(R.id.slidingTabs);
    tablayout.setupWithViewPager(viewPager);

    /*final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

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
    });*/
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new TopRatedMoviesFragment(), "TOP RATED");
    adapter.addFragment(new TopRatedMoviesFragment(), "UPCOMING");
    adapter.addFragment(new TopRatedMoviesFragment(), "NOW PLAYING");
    adapter.addFragment(new TopRatedMoviesFragment(), "POPULAR");
    viewPager.setAdapter(adapter);
  }


}
