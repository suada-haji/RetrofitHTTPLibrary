package com.android.java.miss.retrofithttplibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.java.miss.retrofithttplibrary.R;
import com.android.java.miss.retrofithttplibrary.model.MovieCasts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
  private ArrayList<MovieCasts> casts;
  private int rowLayout;
  private static Context context;
  private static final String TAG = CastAdapter.class.getSimpleName();

  public static class CastViewHolder extends RecyclerView.ViewHolder {
    ImageView castImage;
    TextView castName;
    TextView castCharacter;

    public CastViewHolder(View view) {
      super(view);

      castImage = (ImageView) view.findViewById(R.id.cast_image);
      castName = (TextView) view.findViewById(R.id.cast_name);
      castCharacter = (TextView) view.findViewById(R.id.cast_character);
    }
  }

  public CastAdapter(ArrayList<MovieCasts> casts, int rowLayout, Context context) {
    this.casts = casts;
    this.rowLayout = rowLayout;
    this.context = context;
  }

  @Override
  public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
    return new CastViewHolder(view);
  }

  @Override
  public void onBindViewHolder(CastViewHolder holder, int position) {
    holder.castName.setText(casts.get(position).getName());
    holder.castCharacter.setText(casts.get(position).getCharacter());
    Picasso.with(context).load("http://image.tmdb.org/t/p/original/" + casts.get(position).getProfilePath()).into(holder.castImage);
  }

  @Override
  public int getItemCount() {
    return casts.size();
  }



}
