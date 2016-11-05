package com.android.java.miss.retrofithttplibrary.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Movie {

  @SerializedName("poster_path")
  private String posterPath;
  @SerializedName("adult")
  private boolean adult;
  @SerializedName("overview")
  private String overview;
  @SerializedName("release_date")
  private String releaseDate;
  @SerializedName("genre_ids")
  private ArrayList<Integer> genreIds = new ArrayList<>();
  @SerializedName("id")
  private int id;
  @SerializedName("original_title")
  private String originalTitle;
  @SerializedName("original_language")
  private String originalLanguage;
  @SerializedName("title")
  private String title;
  @SerializedName("backdrop_path")
  private String backdropPath;
  @SerializedName("popularity")
  private double popularity;
  @SerializedName("vote_count")
  private int voteCount;
  @SerializedName("video")
  private boolean video;
  @SerializedName("vote_average")
  private double voteAverage;

  public Movie() {

  }

  public Movie(String posterPath, boolean adult, String overview, String releaseDate, ArrayList<Integer> genreIds, int id,String originalTitle, String originalLanguage, String title, String backdropPath, Double popularity,
  Integer voteCount, Boolean video, Double voteAverage) {
    this.posterPath = posterPath;
    this.adult = adult;
    this.overview = overview;
    this.releaseDate = releaseDate;
    this.genreIds = genreIds;
    this.id = id;
    this.originalTitle = originalTitle;
    this.originalLanguage = originalLanguage;
    this.title = title;
    this.backdropPath = backdropPath;
    this.popularity = popularity;
    this.voteCount = voteCount;
    this.video = video;
    this.voteAverage = voteAverage;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getReleaseDate() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    SimpleDateFormat formatted = new SimpleDateFormat("yyyy");

    try {
      date = format.parse(releaseDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    releaseDate = formatted.format(date);
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public ArrayList<Integer> getGenreIds() {
    return genreIds;
  }

  public void setGenreIds(ArrayList<Integer> genreIds) {
    this.genreIds = genreIds;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public double getPopularity() {
    return popularity;
  }

  public void setPopularity(double popularity) {
    this.popularity = popularity;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }

  public boolean getVideo() {
    return video;
  }

  public void setVideo(boolean video) {
    this.video = video;
  }

  public double getVoteAverage() {
    return voteAverage;
  }

  public void setVoteAverage(double voteAverage) {
    this.voteAverage = voteAverage;
  }
}
