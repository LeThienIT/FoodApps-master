package com.android.fooddelivey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.fooddelivey.R;
import com.android.fooddelivey.adapter.DetailPageAdapter;
import com.android.fooddelivey.model.Food;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ImageView imgDetails, imgLove;
    private ViewPager mViewPager;
    private DetailPageAdapter detailPageAdapter;
    private String mIngridient1, mIngridient2,mIngridient3,mIngridient4,mIngridient5,mIngridient6, mRecipe;
    private int check = 0;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mapping();
        init();
        getDetail();
    }

    private void getDetail() {
        Intent intent = getIntent();
        Food food = (Food) intent.getSerializableExtra("detail");
        Glide.with(this).load(food.getAvatar()).into(imgDetails);

        String txtvIngredient1 = food.getIngredient1();
        mIngridient1 = txtvIngredient1;

        String txtvIngredient2 = food.getIngredient2();
        mIngridient2 = txtvIngredient2;

        String txtvIngredient3 = food.getIngredient3();
        mIngridient3 = txtvIngredient3;

        String txtvIngredient4 = food.getIngredient4();
        mIngridient4 = txtvIngredient4;

        String txtvIngredient5 = food.getIngredient5();
        mIngridient5 = txtvIngredient5;

        String txtvIngredient6 = food.getIngredient6();
        mIngridient6 = txtvIngredient6;

        String recipe = food.getInstructions();
        id = food.getId();
        // bổ sung yêu thích
        mRecipe = recipe;
    }

    public TabLayout getmTabLayout() {
        return mTabLayout;
    }

    public String getmIngridient1() {
        return mIngridient1;
    }

    public String getmIngridient2() {
        return mIngridient2;
    }

    public String getmIngridient3() {
        return mIngridient3;
    }

    public String getmIngridient4() {
        return mIngridient4;
    }

    public String getmIngridient5() {
        return mIngridient5;
    }

    public String getmIngridient6() {
        return mIngridient6;
    }

    public String getmRecipe() {
        return mRecipe;
    }

    private void init() {
        detailPageAdapter = new DetailPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(detailPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void mapping() {
        mTabLayout = findViewById(R.id.tabDetail);
        mViewPager = findViewById(R.id.viewPagerDetail);
        imgDetails = findViewById(R.id.imgDetail);
    }
}