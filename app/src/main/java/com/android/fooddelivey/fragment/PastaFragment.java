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
 * Use the {@link PastaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastaFragment extends Fragment implements FoodAdapter.OnFoodListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PastaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastaFragment newInstance(String param1, String param2) {
        PastaFragment fragment = new PastaFragment();
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

    private RecyclerView mrcvPasta;
    private FoodAdapter foodAdapter;
    private List<Food> listPasta;

    private RecyclerView rcvCategoryIdPasta;
    private CategoryIdAdapter categoryIdAdapter;
    private List<CategoryId> listCateIdPasta;

    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pasta, container, false);
        mapping(view);
        init();
        intiCateId();
        return view;
    }

    private void intiCateId() {
        listCateIdPasta = new ArrayList<>();
        categoryIdAdapter = new CategoryIdAdapter(getContext(), listCateIdPasta);
        rcvCategoryIdPasta.setHasFixedSize(true);
        rcvCategoryIdPasta.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvCategoryIdPasta.setAdapter(categoryIdAdapter);
        GetDataCategoryIdPasta();
    }

    private void GetDataCategoryIdPasta() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Api.GET_URL_CATE_ID + 6, null, new Response.Listener<JSONArray>() {
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
                                listCateIdPasta.add(new CategoryId(id, name, avatar, categoryid,cateid, description));
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
        listPasta = new ArrayList<>();
        foodAdapter = new FoodAdapter(getContext(), listPasta, this);
        mrcvPasta.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mrcvPasta.setLayoutManager(gridLayoutManager);
        mrcvPasta.setAdapter(foodAdapter);
        createDummyData();
//        GetDataPastaFood();
    }

    private void GetDataPastaFood() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Api.GET_URL_FOOD + 6, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        listPasta.add(new Food(jsonObject.getInt("id"),
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
        mrcvPasta = view.findViewById(R.id.rcvPasta);
        rcvCategoryIdPasta = view.findViewById(R.id.rcvCategoryIdPasta);
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
                GetDataPastaFood();
                progressBar.setVisibility(View.GONE);
            }
        }, (2000));
    }

}