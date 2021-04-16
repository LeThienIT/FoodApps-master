package com.android.fooddelivey.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.android.fooddelivey.fragment.BeefFragment;
import com.android.fooddelivey.fragment.ChickenFragment;
import com.android.fooddelivey.fragment.DessertFragment;
import com.android.fooddelivey.fragment.LambFragment;
import com.android.fooddelivey.fragment.MiscellaneousFragment;
import com.android.fooddelivey.fragment.PastaFragment;
import com.android.fooddelivey.fragment.PorkFragment;
import com.android.fooddelivey.fragment.SeaFoodFramgment;
import com.android.fooddelivey.fragment.SideFragment;
import com.android.fooddelivey.fragment.StarterFragment;
import com.android.fooddelivey.fragment.VeganFragment;
import com.android.fooddelivey.fragment.VegetarianFragment;

public class FoodPagerAdapter extends FragmentStatePagerAdapter {
    public FoodPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new ChickenFragment();
            case 2:
                return new DessertFragment();
            case 3:
                return new LambFragment();
            case 4:
                return new MiscellaneousFragment();
            case 5:
                return new PastaFragment();
            case 6:
                return new PorkFragment();
            case 7:
                return new SeaFoodFramgment();
            case 8:
                return new SideFragment();
            case 9:
                return new StarterFragment();
            case 10:
                return new VeganFragment();
            case 11:
                return new VegetarianFragment();
            default:
                return new BeefFragment();
        }
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Beef";
                break;
            case 1:
                title = "Chicken";
                break;
            case 2:
                title = "Dessert";
                break;
            case 3:
                title = "Lamb";
                break;
            case 4:
                title = "Miscellaneous";
                break;
            case 5:
                title = "Pasta";
                break;
            case 6:
                title = "Pork";
                break;
            case 7:
                title = "Seafood";
                break;
            case 8:
                title = "Side";
                break;
            case 9:
                title = "Starter";
                break;
            case 10:
                title = "Vegan";
                break;
            case 11:
                title = "Vegatarian";
                break;
        }
        return title;
    }
}
