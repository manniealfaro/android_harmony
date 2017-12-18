package com.example.manuel.harmony.Friends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.manuel.harmony.BaseActivity;
import com.example.manuel.harmony.Climb.ClimbListFragment;
import com.example.manuel.harmony.Climb.ClimbMapFragment;
import com.example.manuel.harmony.R;
import com.example.manuel.harmony.helpers.BottomNavigationViewHelper;
import com.example.manuel.harmony.utilities.Utility;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class FriendsActivity extends BaseActivity  implements View.OnClickListener {

    private static final String TAG = "FriendsActivity";
    private static final int ACTIVITY_NUM = 4;
    private Context mContext = FriendsActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Log.d(TAG, "onCreate: started.");

        ((Button)findViewById(R.id.green_theme_btn)).setOnClickListener(this);
        ((Button)findViewById(R.id.red_theme_btn)).setOnClickListener(this);
        ((Button)findViewById(R.id.yellow_theme_btn)).setOnClickListener(this);

        setupBottomNavigationView();
    }

    protected void onStart(Bundle savedInstance) {
        setContentView(R.layout.activity_friends);
        Log.d(TAG, "onStart: started.");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.green_theme_btn:
                Utility.setTheme(getApplicationContext(), 1);
                recreateActivity();
                break;
            case R.id.yellow_theme_btn:
                Utility.setTheme(getApplicationContext(), 2);
                recreateActivity();
                break;
            case R.id.red_theme_btn:
                Utility.setTheme(getApplicationContext(), 3);
                recreateActivity();
                break;
        }
    }

    public void recreateActivity() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    /**
     * Buttom navigation view
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationViewEx);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);

        BottomNavigationViewHelper.enableNAvigation(mContext, bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}
