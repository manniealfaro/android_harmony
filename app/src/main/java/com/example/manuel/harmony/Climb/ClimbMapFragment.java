package com.example.manuel.harmony.Climb;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manuel.harmony.R;

/**
 * Created by manuel on 29/11/2017.
 */

public class ClimbMapFragment extends Fragment {

    public static final String TAG = "ClimbMapFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_climb_map, container, false);

        return view;
    }
}
