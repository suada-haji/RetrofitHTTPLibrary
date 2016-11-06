package com.android.java.miss.retrofithttplibrary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.java.miss.retrofithttplibrary.R;
import com.android.java.miss.retrofithttplibrary.fragments.DetailFragment;

public class DetailActivity extends AppCompatActivity {
  public static final String MOVIE_ID = "MOVIE_ID";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_detail);
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(" Movie");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    if (savedInstanceState == null) {
      DetailFragment fragment = new DetailFragment();
      String id = getIntent().getStringExtra(MainActivity.MOVIE_BUNDLE);
      Bundle bundle = new Bundle();
      bundle.putString(MOVIE_ID, id);
      fragment.setArguments(bundle);

      getFragmentManager().beginTransaction()
              .add(R.id.detailContainer, fragment)
              .commit();
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
    }
    return true;
  }
}
