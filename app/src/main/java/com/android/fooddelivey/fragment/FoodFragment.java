package com.android.fooddelivey.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.fooddelivey.R;
import com.android.fooddelivey.activity.MainActivity;
import com.android.fooddelivey.adapter.FoodPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodFragment extends Fragment {

    public TabLayout mTabLayout;
    public static ViewPager mViewPager;
    private Toolbar toolbarFood;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodFragment newInstance(String param1, String param2) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        mapping(view);
        HomeFragment homeFragment = new HomeFragment();
        setUpViewPager(homeFragment.viTri);
        return view;
    }

    private void setUpViewPager(int position) {
        FoodPagerAdapter foodPageAdapter = new FoodPagerAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(foodPageAdapter);
        mViewPager.setCurrentItem(position);
//        mTabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void mapping(View view) {
        mTabLayout = view.findViewById(R.id.tabFood);
        mViewPager = view.findViewById(R.id.viewPagerFood);
        toolbarFood = view.findViewById(R.id.toolbarFood);

//        toolbarFood.setNavigationIcon(R.drawable.ic_back);
//        toolbarFood.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().onBackPressed();
//            }
//        });
    }
}