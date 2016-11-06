# Using Retrofit 2.0 as REST client

Retrofit is a type-safe REST client for Android developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. 

To work with Retrofit you need basically three classes.
- Model class which is used to map the JSON data to
- Interfaces which defines the possible HTTP operations
- Retrofit.Builder class - Instance which uses the interface. Builder API which allows defining the URL end point for the HTTP operation.

For this tutorial I used [TMDb API](https://www.themoviedb.org/documentation/api). In order to use this API it is necessary to obtain the API key.

|<img src="/screenshots/screenshot2.png">|<img src="/screenshots/screenshot3.png">|<img src="/screenshots/screenshot4.png">|
| ------------- |:-------------:| -----:|

### Setup
Make sure to require Internet permissions in your AndroidManifest.xml file:
```javascript
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-permission android:name="android.permission.INTERNET" />
</manifest>
```

Add the following to your app/build.gradle file:
```javascript
dependencies {
    compile 'com.google.code.gson:gson:2.6.2'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'  

}
```

### Creating the Retrofit instance
To send out network requests to an API, we need to use the [Retrofit builder](http://square.github.io/retrofit/2.x/retrofit/retrofit2/Retrofit.Builder.html) class and specify the base URL for the service.
```javascript
// Trailing slash is needed
public static final String BASE_URL = "http://api.themoviedb.org/3/";
  private static Retrofit retrofit = null;

  public static Retrofit getClient() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
    }
    return retrofit;
```

### Define the Endpoints
The endpoints are defined inside of an interface using special retrofit annotations to encode details about the parameters and request method. In addition, the return value is always a parameterized `Call<T>` object such as `Call<User>`. If you do not need any type-specific response, you can specify return value as simply `Call<ResponseBody>`.
```javascript
public interface ApiInterface {
  @GET("movie/top_rated")
  Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

  @GET("movie/upcoming")
  Call<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey);

  @GET("movie/now_playing")
  Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

  @GET("movie/popular")
  Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

  @GET("movie/{id}")  
  Call<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

  @GET("movie/{id}/casts")
  Call<CastResponse> getMovieCasts(@Path("id") int id, @Query("api_key") String apiKey);
}
```

Notice that each endpoint specifies an annotation of the HTTP method (GET, POST, etc.) and method that will be used to dispatch the network call. Note that the parameters of this method can also have special annotations:

| Annotation        | Description       |
| ------------- |:-------------|
| @Path     | variable substitution for the API endpoint (For example movie id will be swapped for `{id}` in the URL endpoint).| 
|@Query      |specifies the query key name with the value of the annotated parameter.    |
| @Body |payload for the POST call    |

### Accessing the API
If we want to consume the API asynchronously, we call the service as follows:
```javascript
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
```

In the above, Retrofit will download and parse the API data on a background thread, and then deliver the results back to the UI thread via the onResponse or onFailure method.
