package com.android.fooddelivey.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.fooddelivey.R;
import com.android.fooddelivey.activity.DetailActivity;
import com.android.fooddelivey.adapter.CategoryIdAdapter;
import com.android.fooddelivey.adapter.FoodAdapter;
import com.android.fooddelivey.model.CategoryId;
import com.android.fooddelivey.model.Food;
import com.android.fooddelivey.server.Api;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiscellaneousFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiscellaneousFragment extends Fragment implements FoodAdapter.OnFoodListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MiscellaneousFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiscellaneousFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MiscellaneousFragment newInstance(String param1, String param2) {
        MiscellaneousFragment fragment = new MiscellaneousFragment();
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

    private RecyclerView mrcvMiscellaneous;
    private FoodAdapter foodAdapter;
    private List<Food> listMiscellaneous;

    private RecyclerView rcvCategoryIdMiscellaneous;
    private CategoryIdAdapter categoryIdAdapter;
    private List<CategoryId> listCateIdMiscellaneous;

    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_miscellaneous, container, false);
        mapping(view);
        init();
        intiCateId();
        return view;
    }

    private void intiCateId() {
        listCateIdMiscellaneous = new ArrayList<>();
        categoryIdAdapter = new CategoryIdAdapter(getContext(), listCateIdMiscellaneous);
        rcvCategoryIdMiscellaneous.setHasFixedSize(true);
        rcvCategoryIdMiscellaneous.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvCategoryIdMiscellaneous.setAdapter(categoryIdAdapter);
        GetDataCategoryIdMiscellaneous();
    }

    private void GetDataCategoryIdMiscellaneous() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Api.GET_URL_CATE_ID + 5, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String avatar = jsonObject.getString("avatar");
                        int categoryid = jsonObject.getInt("categoryid");
                        int cateid  = jsonObject.getInt("cateid");
                        String description = jsonObject.getString("description");

                        switch (categoryid) {
                            case 1:
                                listCateIdMiscellaneous.add(new CategoryId(id, name, avatar, categoryid,cateid, description));
                                categoryIdAdapter.notifyDataSetChanged();
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

    private void init() {
        listMiscellaneous = new ArrayList<>();
        foodAdapter = new FoodAdapter(getContext(), listMiscellaneous, this);
        mrcvMiscellaneous.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mrcvMiscellaneous.setLayoutManager(gridLayoutManager);
        mrcvMiscellaneous.setAdapter(foodAdapter);
        createDummyData();
//        GetDataMiscellaneousFood();
    }

    private void GetDataMiscellaneousFood() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Api.GET_URL_FOOD + 5, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        listMiscellaneous.add(new Food(jsonObject.getInt("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("avatar"),
                                jsonObject.getString("ingredient1"),
                                jsonObject.getString("ingredient2"),
                                jsonObject.getString("ingredient3"),
                                jsonObject.getString("ingredient4"),
                                jsonObject.getString("ingredient5"),
                                jsonObject.getString("ingredient6"),
                                jsonObject.getString("instructions"),
                                jsonObject.getInt("categoryid")));

                        foodAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
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
        mrcvMiscellaneous = view.findViewById(R.id.rcvMiscellaneous);
        rcvCategoryIdMiscellaneous = view.findViewById(R.id.rcvCategoryIdMiscellaneous);
        progressBar = view.findViewById(R.id.progressBar);
    }

    @Override
    public void onFoodClick(Food food) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("detail", food);
        startActivity(intent);
    }

    private void createDummyData(){
        progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GetDataMiscellaneousFood();
                progressBar.setVisibility(View.GONE);
            }
        }, (2000));
    }
}