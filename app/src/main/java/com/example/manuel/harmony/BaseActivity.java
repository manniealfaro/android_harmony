package com.example.manuel.harmony;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.manuel.harmony.Authentication.LogInActivity;
import com.example.manuel.harmony.utilities.Utility;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by manuel on 13/12/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private final static int THEME_GREEN = 1;
    private final static int THEME_YELLOW = 2;
    private final static int THEME_RED = 3;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateTheme();

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(BaseActivity.this, LogInActivity.class));
                    finish();
                }
            }
        };
    }

    protected void onStart(Bundle savedInstance) {
        super.onStart();

        auth.addAuthStateListener(authListener);

        FirebaseUser user = auth.getCurrentUser();
        updateUI(user);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    private void updateUI(FirebaseUser user) {


    }

    public void signOut() {
        auth.signOut();
    }

    public void updateTheme() {
            if (Utility.getTheme(getApplicationContext()) <= THEME_GREEN) {
                setTheme(com.example.manuel.harmony.R.style.AppTheme_Green);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
            } else if (Utility.getTheme(getApplicationContext()) <= THEME_YELLOW) {
                setTheme(com.example.manuel.harmony.R.style.AppTheme_Yellow);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
            } else if (Utility.getTheme(getApplicationContext()) == THEME_RED) {
                setTheme(com.example.manuel.harmony.R.style.AppTheme_Red);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
            }
    }
}
