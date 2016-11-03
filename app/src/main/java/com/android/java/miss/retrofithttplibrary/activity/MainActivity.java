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
import com.android.java.miss.retrofithttplibrary.fragments.NowPlayingMoviesFragment;
import com.android.java.miss.retrofithttplibrary.fragments.PopularMoviesFragment;
import com.android.java.miss.retrofithttplibrary.fragments.TopRatedMoviesFragment;
import com.android.java.miss.retrofithttplibrary.fragments.UpcomingMoviesFragment;


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


    if (API_KEY.isEmpty()) {
      Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG);
      return;
    }

    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    setupViewPager(viewPager);

    TabLayout tablayout = (TabLayout) findViewById(R.id.slidingTabs);
    tablayout.setupWithViewPager(viewPager);

  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new TopRatedMoviesFragment(), "TOP RATED");
    adapter.addFragment(new UpcomingMoviesFragment(), "UPCOMING");
    adapter.addFragment(new NowPlayingMoviesFragment(), "NOW PLAYING");
    adapter.addFragment(new PopularMoviesFragment(), "POPULAR");
    viewPager.setAdapter(adapter);
  }


}
