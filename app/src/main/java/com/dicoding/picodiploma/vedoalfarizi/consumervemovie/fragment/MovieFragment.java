package com.dicoding.picodiploma.vedoalfarizi.consumervemovie.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.DetailMovieActivity;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.MainActivity;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.R;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.adapter.MovieAdapter;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.model.Movie;

import java.util.ArrayList;
import java.util.Objects;

import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.KEY_EXTRA_MOVIES;
import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.LOADER_MOVIE;
import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.URI_MOVIE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private MovieAdapter movieAdapter;
    private ProgressBar pbData;
    private RecyclerView rvMovie;
    private ArrayList<Movie> movieArrayList = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) Objects.requireNonNull(getActivity())).setActionBarTitle("Movies");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pbData = view.findViewById(R.id.pb_data);
        rvMovie = view.findViewById(R.id.rv_movie);

        movieAdapter = new MovieAdapter();
        movieAdapter.setMovies(movieArrayList);
        movieAdapter.notifyDataSetChanged();
        movieAdapter.setOnItemClick(new MovieAdapter.OnItemClick() {
            @Override
            public void click(Movie m) {
                Intent detailActivityIntent = new Intent(getActivity(), DetailMovieActivity.class);
                detailActivityIntent.putExtra(KEY_EXTRA_MOVIES, m);
                startActivity(detailActivityIntent);
            }
        });
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(movieAdapter);
        pbData.setVisibility(View.VISIBLE);
        getLoaderManager().initLoader(LOADER_MOVIE, null, cursorLoaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<Cursor> cursorLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
            if(i == LOADER_MOVIE){
                return new CursorLoader(Objects.requireNonNull(getContext()),
                        URI_MOVIE,
                        new String[]{"favorite_movies"},
                        null, null, null);
            }
            throw new IllegalArgumentException();
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
            if(loader.getId() == LOADER_MOVIE){
                if(cursor == null){
                    movieAdapter.setMovies(null);
                }else{
                    ArrayList<Movie> movies = new ArrayList<>();
                    for(int i=0; i<cursor.getCount(); i++){
                        cursor.moveToPosition(i);
                        Movie movie = new Movie(cursor);
                        movies.add(movie);
                    }
                    movieAdapter.setMovies(movies);
                }
                rvMovie.setAdapter(movieAdapter);
                pbData.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            if(loader.getId() == LOADER_MOVIE){
                movieAdapter.setMovies(null);
            }
        }
    };
}
