package com.android.fooddelivey.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.android.fooddelivey.fragment.IngridientsFragment;
import com.android.fooddelivey.fragment.RecipeFragment;

public class DetailPageAdapter extends FragmentStatePagerAdapter {

    public DetailPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new RecipeFragment();
            default:
                return new IngridientsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Ingredients";
                break;
            case 1:
                title = "Recipe";
                break;
        }

        return title;
    }
}
