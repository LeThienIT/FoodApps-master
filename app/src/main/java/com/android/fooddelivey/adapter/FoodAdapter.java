package com.android.fooddelivey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.fooddelivey.R;
import com.android.fooddelivey.model.Food;
import com.bumptech.glide.Glide;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {

    Context context;
    private List<Food> listFood;
    private OnFoodListener mOnFoodListener;

    public FoodAdapter(Context context, List<Food> listFood, OnFoodListener mOnFoodListener) {
        this.context = context;
        this.listFood = listFood;
        this.mOnFoodListener = mOnFoodListener;
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_listfood, null);
        FoodHolder foodHolder = new FoodHolder(view, mOnFoodListener);
        return foodHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodHolder holder, int position) {
        Food food = listFood.get(position);
        holder.nameFood.setText(food.getName());
        Glide.with(context).load(food.getAvatar()).into(holder.imgFood);
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    class FoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgFood;
        TextView nameFood;
        OnFoodListener onFoodListener;

        public FoodHolder(@NonNull View itemView, OnFoodListener onFoodListener) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            nameFood = itemView.findViewById(R.id.nameFood);

            this.onFoodListener = onFoodListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onFoodListener.onFoodClick(listFood.get(getAdapterPosition()));
        }
    }

    public interface OnFoodListener {
        void onFoodClick(Food food);
    }
}
