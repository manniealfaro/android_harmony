package com.example.manuel.harmony.Logbook;


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

public class LogbookListFragment extends Fragment {

    public static final String TAG = "LogbookListFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logbook_list, container, false);

        return view;
    }
}
