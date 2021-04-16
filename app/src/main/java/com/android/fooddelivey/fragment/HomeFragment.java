package com.android.fooddelivey.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.fooddelivey.R;
import com.android.fooddelivey.activity.MainActivity;
import com.android.fooddelivey.adapter.CategoryAdapter;
import com.android.fooddelivey.model.Category;
import com.android.fooddelivey.server.Api;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements CategoryAdapter.OnCateListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    private List<Category> listFood;
    private RecyclerView recyclerCategory;
    private CategoryAdapter mCategoryAdapter;
    public static int viTri = 0;

    private ProgressBar progressBarHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mapping(view);
        init();
        return view;
    }

    private void init() {
        listFood = new ArrayList<>();
        mCategoryAdapter = new CategoryAdapter(getContext(), listFood, this, "food");
        recyclerCategory.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),
                2, GridLayoutManager.VERTICAL, false);
        recyclerCategory.setLayoutManager(gridLayoutManager);
        recyclerCategory.setAdapter(mCategoryAdapter);
//        createDummyDataHome();
        GetAllCategory();
    }

    private void GetAllCategory() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Api.GET_URL_CATE, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String avatar = jsonObject.getString("avatar");
                        int categoryId = jsonObject.getInt("categoryid");

                        switch (categoryId) {
                            case 1:
                                listFood.add(new Category(id, name, avatar, categoryId));
                                mCategoryAdapter.notifyDataSetChanged();
                                break;
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void mapping(View view) {
        recyclerCategory = view.findViewById(R.id.recyclerCategory);
//        progressBarHome = view.findViewById(R.id.progressBarHome);
    }

    @Override
    public void onCateClick(int position, String tag) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        MainActivity mainActivity = new MainActivity();
        switch (tag) {
            case "food":
                fragmentTransaction.replace(R.id.frame_container, new FoodFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                mainActivity.navigation.getMenu().findItem(R.id.navigation_Food).setChecked(true);
                break;
        }
        viTri = position;
    }

//    private void createDummyDataHome(){
//        progressBarHome.setVisibility(View.VISIBLE);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                GetAllCategory();
//                progressBarHome.setVisibility(View.GONE);
//            }
//        }, (1000));
//    }
}