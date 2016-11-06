package com.android.java.miss.retrofithttplibrary.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by suadahaji.
 */

public class CastResponse {
  @SerializedName("id")
  private int id;
  @SerializedName("cast")
  private ArrayList<MovieCasts> casts;
  @SerializedName("crew")
  private ArrayList<MovieCrew> crews;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ArrayList<MovieCasts> getCasts() {
    return casts;
  }

  public void setCasts(ArrayList<MovieCasts> casts) {
    this.casts = casts;
  }

  public ArrayList<MovieCrew> getCrews() {
    return crews;
  }

  public void setCrews(ArrayList<MovieCrew> crews) {
    this.crews = crews;
  }
}
