package com.dicoding.picodiploma.vedoalfarizi.consumervemovie.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.R;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.model.Movie;

import java.util.ArrayList;

import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.POSTER_URL;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> movies;
    private OnItemClick onItemClick;

    public MovieAdapter(){

    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int i) {
        Movie movie = movies.get(i);

        String poster_path = POSTER_URL +movie.getPoster();

        Glide.with(movieViewHolder.itemView.getContext())
                .load(poster_path)
                .into(movieViewHolder.imgPoster);

        movieViewHolder.tvTitle.setText(movie.getTitle());
        movieViewHolder.tvRating.setText(String.valueOf(movie.getRating()));
        movieViewHolder.tvReleaseDate.setText(movie.getRelease_date());
        movieViewHolder.rbRating.setRating(movie.getRating() / 2);

        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.click(movies.get(movieViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(movies != null){
            return movies.size();
        }else{
            return 0;
        }

    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitle, tvRating, tvReleaseDate;
        RatingBar rbRating;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
            rbRating = itemView.findViewById(R.id.rb_rating);
        }
    }

    public interface OnItemClick{
        void click(Movie m);
    }
}
