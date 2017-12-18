package com.example.manuel.harmony.Home;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import com.github.florent37.camerafragment.CameraFragmentOld;

import com.example.manuel.harmony.R;
import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.configuration.Configuration;

/**
 * Created by manuel on 29/11/2017.
 */

public class CameraFragmentOld extends Fragment {

    public static final String TAG = "CameraFragmentOld";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_camera, container, false);

        return view;
    }
}
