package com.dicoding.picodiploma.vedoalfarizi.consumervemovie.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.MainActivity;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {


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

}
