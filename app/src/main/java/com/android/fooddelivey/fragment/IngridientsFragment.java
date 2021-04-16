package com.android.fooddelivey.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.fooddelivey.R;
import com.android.fooddelivey.activity.DetailActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IngridientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngridientsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IngridientsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IngridientsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IngridientsFragment newInstance(String param1, String param2) {
        IngridientsFragment fragment = new IngridientsFragment();
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

    private DetailActivity mDetailActivity;
    private TextView txtvIngridient1, txtvIngridient2, txtvIngridient3, txtvIngridient4, txtvIngridient5, txtvIngridient6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingridients, container, false);
        mDetailActivity = (DetailActivity) getActivity();
        txtvIngridient1 = view.findViewById(R.id.txtvIngredient1);
        txtvIngridient2 = view.findViewById(R.id.txtvIngredient2);
        txtvIngridient3 = view.findViewById(R.id.txtvIngredient3);
        txtvIngridient4 = view.findViewById(R.id.txtvIngredient4);
        txtvIngridient5 = view.findViewById(R.id.txtvIngredient5);
        txtvIngridient6 = view.findViewById(R.id.txtvIngredient6);

        txtvIngridient1.setText(mDetailActivity.getmIngridient1());
        txtvIngridient2.setText(mDetailActivity.getmIngridient2());
        txtvIngridient3.setText(mDetailActivity.getmIngridient3());
        txtvIngridient4.setText(mDetailActivity.getmIngridient4());
        txtvIngridient5.setText(mDetailActivity.getmIngridient5());
        txtvIngridient6.setText(mDetailActivity.getmIngridient6());
        return view;
    }
}