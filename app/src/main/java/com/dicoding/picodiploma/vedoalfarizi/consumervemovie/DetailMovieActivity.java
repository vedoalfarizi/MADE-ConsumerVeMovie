package com.dicoding.picodiploma.vedoalfarizi.consumervemovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.model.Movie;

import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.KEY_EXTRA_MOVIES;
import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.POSTER_URL;

public class DetailMovieActivity extends AppCompatActivity {
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ImageView imgPoster = findViewById(R.id.img_poster);
        TextView txtTitle = findViewById(R.id.tv_title);
        TextView txtRating = findViewById(R.id.tv_rating);
        TextView txtOverview = findViewById(R.id.tv_overview);
        TextView txtReleaseDate = findViewById(R.id.tv_release_date);
        RatingBar rbRating = findViewById(R.id.rb_rating);

        Intent intent = getIntent();
        if (intent != null) {
            movie = intent.getParcelableExtra(KEY_EXTRA_MOVIES);
            txtTitle.setText(movie.getTitle());
            txtRating.setText(String.valueOf(movie.getRating()));
            txtOverview.setText(movie.getOverview());
            txtReleaseDate.setText(movie.getRelease_date());
            rbRating.setRating(movie.getRating() / 2);

            String poster_path = POSTER_URL + movie.getPoster();
            Glide.with(imgPoster).load(poster_path).into(imgPoster);
        }
    }
}
