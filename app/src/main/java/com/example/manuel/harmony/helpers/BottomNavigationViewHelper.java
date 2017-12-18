package com.example.manuel.harmony.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.MenuItem;

import com.example.manuel.harmony.Camera.CameraActivity;
import com.example.manuel.harmony.Climb.ClimbActivity;
import com.example.manuel.harmony.Friends.FriendsActivity;
import com.example.manuel.harmony.Logbook.LogbookActivity;
import com.example.manuel.harmony.Home.MainActivity;
import com.example.manuel.harmony.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.File;
import java.io.IOException;

/**
 * Created by manuel on 29/11/2017.
 */

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";
    static final int REQUEST_TAKE_PHOTO = 1;


    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: settingup BottonNavigationView");
    }

    public static void enableNAvigation(final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intentToHome = new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intentToHome);
                        return true;
                    case R.id.navigation_camera:
                        Intent intentToCamera = new Intent(context, CameraActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intentToCamera);
                        return true;
                    case R.id.navigation_map:
                        Intent intentToClimb = new Intent(context, ClimbActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intentToClimb);
                        return true;
                    case R.id.navigation_logbook:
                        Intent intentToLogbook = new Intent(context, LogbookActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intentToLogbook);
                        return true;
                    case R.id.navigation_user:
                        Intent intentToProfile = new Intent(context, FriendsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intentToProfile);
                        return true;
                }
                return false;
            }
        });
    }
}
