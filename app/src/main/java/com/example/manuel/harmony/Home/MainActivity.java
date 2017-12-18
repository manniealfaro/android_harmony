package com.example.manuel.harmony.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.manuel.harmony.Authentication.LogInActivity;
import com.example.manuel.harmony.BaseActivity;
import com.example.manuel.harmony.Home.Adapters.SectionsPagerAdapter;
import com.example.manuel.harmony.R;
import com.example.manuel.harmony.helpers.BottomNavigationViewHelper;
import com.example.manuel.harmony.tour.IntroActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MAINACTIVITY";
    private static final int ACTIVITY_NUM = 0;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    private Context mContext = MainActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting.");

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
                    startActivity(new Intent(MainActivity.this, LogInActivity.class));
                    finish();
                }
            }
        };

        boolean isFirst = MyPreferences.isFirst(MainActivity.this);
        if(isFirst){
            startActivity(new Intent(this, IntroActivity.class));
        }

        setupBottomNavigationView();
        setupViewPager();
    }

    protected void onStart(Bundle savedInstance) {
        super.onStart();
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onStart: started.");

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

    private void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new ProfileFragment());

        ViewPager viewPager = (ViewPager) findViewById(R.id.body);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.toolbar);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_photo_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_friends);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_user);

        viewPager.setCurrentItem(1);
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

    private void updateUI(FirebaseUser user) {


    }

    public void signOut() {
        auth.signOut();
    }


}

class MyPreferences {

    private static final String MY_PREFERENCES = "my_preferences";

    public static boolean isFirst(Context context){
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final boolean first = reader.getBoolean("is_first", true);
        if(first){
            final SharedPreferences.Editor editor = reader.edit();
            editor.putBoolean("is_first", false);
            editor.commit();
        }
        return first;
    }

}
