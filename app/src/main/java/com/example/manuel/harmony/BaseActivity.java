package com.example.manuel.harmony;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.manuel.harmony.utilities.Utility;

/**
 * Created by manuel on 13/12/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private final static int THEME_GREEN = 1;
    private final static int THEME_YELLOW = 2;
    private final static int THEME_RED = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateTheme();
    }

    public void updateTheme() {
            if (Utility.getTheme(getApplicationContext()) <= THEME_GREEN) {
                setTheme(R.style.AppTheme_Green);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
            } else if (Utility.getTheme(getApplicationContext()) <= THEME_YELLOW) {
                setTheme(R.style.AppTheme_Yellow);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
            } else if (Utility.getTheme(getApplicationContext()) == THEME_RED) {
                setTheme(R.style.AppTheme_Red);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
            }
    }
}
