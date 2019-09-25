package com.dicoding.picodiploma.vedoalfarizi.consumervemovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.model.Show;

import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.KEY_EXTRA_SHOWS;
import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.POSTER_URL;

public class ShowDetailActivity extends AppCompatActivity {
    Show show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        ImageView imgPoster = findViewById(R.id.img_poster);
        TextView txtTitle = findViewById(R.id.tv_title);
        TextView txtRating = findViewById(R.id.tv_rating);
        TextView txtOverview = findViewById(R.id.tv_overview);
        TextView txtPremiereDate = findViewById(R.id.tv_premiere_date);
        RatingBar rbRating = findViewById(R.id.rb_rating);

        Intent intent = getIntent();
        if(intent != null) {
            show = intent.getParcelableExtra(KEY_EXTRA_SHOWS);
            txtTitle.setText(show.getTitle());
            txtRating.setText(String.valueOf(show.getRating()));
            txtOverview.setText(show.getOverview());
            txtPremiereDate.setText(show.getPremiere());
            rbRating.setRating(show.getRating() / 2);

            String poster_path = POSTER_URL + show.getPhoto();
            Glide.with(imgPoster).load(poster_path).into(imgPoster);
        }
    }
}
