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

import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.MainActivity;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.R;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.ShowDetailActivity;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.adapter.ShowAdapter;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.model.Show;

import java.util.ArrayList;
import java.util.Objects;

import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.KEY_EXTRA_SHOWS;
import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.LOADER_SHOW;
import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.URI_SHOW;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {
    private ShowAdapter showAdapter;
    private ProgressBar pbData;
    private RecyclerView rvShow;
    private ArrayList<Show> showArrayList = new ArrayList<>();

    public ShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) Objects.requireNonNull(getActivity())).setActionBarTitle("TV Shows");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pbData = view.findViewById(R.id.pb_data);
        rvShow = view.findViewById(R.id.rv_show);

        showAdapter = new ShowAdapter();
        showAdapter.setShows(showArrayList);
        showAdapter.notifyDataSetChanged();
        showAdapter.setOnItemClick(new ShowAdapter.OnItemClick() {
            @Override
            public void click(Show s) {
                Intent detailActivityIntent = new Intent(getActivity(), ShowDetailActivity.class);
                detailActivityIntent.putExtra(KEY_EXTRA_SHOWS, s);
                startActivity(detailActivityIntent);
            }
        });
        rvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvShow.setAdapter(showAdapter);
        pbData.setVisibility(View.VISIBLE);
        getLoaderManager().initLoader(LOADER_SHOW, null, cursorLoaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<Cursor> cursorLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
            if(i == LOADER_SHOW){
                return  new CursorLoader(Objects.requireNonNull(getContext()),
                        URI_SHOW,
                        new String[]{"favorite_shows"},
                        null, null, null);
            }
            throw new IllegalArgumentException();
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
            if(loader.getId() == LOADER_SHOW){
                if(cursor == null){
                    showAdapter.setShows(null);
                }else{
                    ArrayList<Show> shows = new ArrayList<>();
                    for(int i=0; i<cursor.getCount(); i++){
                        cursor.moveToPosition(i);
                        Show show = new Show(cursor);
                        shows.add(show);
                    }
                    showAdapter.setShows(shows);
                }
                rvShow.setAdapter(showAdapter);
                pbData.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            if(loader.getId() == LOADER_SHOW){
                showAdapter.setShows(null);
            }
        }
    };
}
