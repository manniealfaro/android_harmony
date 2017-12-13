package com.example.manuel.harmony.Logbook;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.manuel.harmony.BaseActivity;
import com.example.manuel.harmony.Home.Adapters.SectionsPagerAdapter;
import com.example.manuel.harmony.R;
import com.example.manuel.harmony.helpers.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class LogbookActivity extends BaseActivity {

    private static final String TAG = "LogbookActivity";
    private static final int ACTIVITY_NUM = 2;
    private Context mContext = LogbookActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);
        Log.d(TAG, "onCreate: started.");

        setupBottomNavigationView();
        setupViewPager();
    }

    protected void onStart(Bundle savedInstance) {
        setContentView(R.layout.activity_logbook);
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
        com.example.manuel.harmony.Home.Adapters.SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LogbookMapFragment());
        adapter.addFragment(new LogbookListFragment());

        ViewPager viewPager = (ViewPager) findViewById(R.id.body);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.toolbar);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_map_location);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_problem_notebook);
    }
}
