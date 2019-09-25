package com.dicoding.picodiploma.vedoalfarizi.consumervemovie;

import android.net.Uri;

public class Provider {
    private static final String AUTHORITY = "com.dicoding.picodiploma.vedoalfarizi.vemovie";
    public static final Uri URI_MOVIE = Uri.parse("content://" + AUTHORITY + "/" + "favorite_movies");
    public static final String POSTER_URL = "https://image.tmdb.org/t/p/w185/";
    public static final int LOADER = 1;
    public static final String KEY_EXTRA_MOVIES = "movie_extra_key";
}
