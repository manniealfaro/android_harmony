package com.example.manuel.harmony.Climb;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.manuel.harmony.R;
import com.example.manuel.harmony.helpers.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ClimbActivity extends AppCompatActivity {

    private static final String TAG = "ClimbActivity";
    private static final int ACTIVITY_NUM = 1;
    private Context mContext = ClimbActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climb);
        Log.d(TAG, "onCreate: started.");

        setupBottomNavigationView();
        setupViewPager();
    }

    protected void onStart(Bundle savedInstance) {
        setContentView(R.layout.activity_climb);
        Log.d(TAG, "onStart: started.");

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

    private void setupViewPager() {
        com.example.manuel.harmony.Home.SectionsPagerAdapter adapter = new com.example.manuel.harmony.Home.SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ClimbMapFragment());
        adapter.addFragment(new ClimbListFragment());

        ViewPager viewPager = (ViewPager) findViewById(R.id.body);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.toolbar);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_map_location);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_problem_notebook);
    }
}
