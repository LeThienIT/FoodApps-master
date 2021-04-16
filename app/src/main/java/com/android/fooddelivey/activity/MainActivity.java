package com.android.fooddelivey.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.fooddelivey.R;
import com.android.fooddelivey.fragment.FoodFragment;
import com.android.fooddelivey.fragment.HomeFragment;
import com.android.fooddelivey.fragment.LoveFragment;
import com.android.fooddelivey.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static BottomNavigationView navigation;
    ActionBar toolbar;
    private Fragment fragment;
    public int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        navigation = findViewById(R.id.navigation);
        loadFragment(new HomeFragment());
        init();
    }

    private void init() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_Home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_Search:
                        fragment = new SearchFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_Food:
                        fragment = new FoodFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_Love:
                        fragment = new LoveFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}