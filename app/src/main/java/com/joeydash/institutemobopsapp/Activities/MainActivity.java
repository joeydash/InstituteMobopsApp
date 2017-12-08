package com.joeydash.institutemobopsapp.Activities;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joeydash.institutemobopsapp.Fragment.FragmentOne;
import com.joeydash.institutemobopsapp.Fragment.FragmentTwo;
import com.joeydash.institutemobopsapp.Helper.Manager;
import com.joeydash.institutemobopsapp.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int INDEX_FIRST_FRAGMENT = 0;
    private static final int INDEX_SECOND_FRAGMENT = 1;
    private static final int NUM_FRAGMENTS = 2;
    public static final String LOG_TAG = "InstituteMobopsApp";
    private Fragment[] fragments;
    private int previousFragmentIndex;






    BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFragments();
        setUpBottomBars();
    }

    private void setUpBottomBars() {
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_frame_one) {
                    changeFragment(INDEX_FIRST_FRAGMENT);
                }else if(tabId == R.id.tab_frame_two){
                    changeFragment(INDEX_SECOND_FRAGMENT);
                }else{
                    Log.d(LOG_TAG,"setUpBottomBars");
                }
            }
        });
    }

    private void initializeFragments() {
        fragments = new Fragment[NUM_FRAGMENTS];

        fragments[INDEX_FIRST_FRAGMENT] = FragmentOne.newInstance();
        fragments[INDEX_SECOND_FRAGMENT] = FragmentTwo.newInstance();
    }
    private void changeFragment(int nextFragmentIndex) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (previousFragmentIndex != nextFragmentIndex)
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

        for (Fragment fragment : fragments)
            if (fragment.isVisible())
                transaction.hide(fragment);

        if (fragments[nextFragmentIndex].isAdded())
            transaction.show(fragments[nextFragmentIndex]);
        else
            transaction.add(R.id.fragment, fragments[nextFragmentIndex]);

        transaction.commit();

        previousFragmentIndex = nextFragmentIndex;
    }

}
