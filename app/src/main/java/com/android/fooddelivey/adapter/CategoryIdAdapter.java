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
import com.android.fooddelivey.model.CategoryId;
import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryIdAdapter extends RecyclerView.Adapter<CategoryIdAdapter.CategoryIdHolder> {

    Context context;
    List<CategoryId> categoryIdList;

    public CategoryIdAdapter(Context context, List<CategoryId> categoryIdList) {
        this.context = context;
        this.categoryIdList = categoryIdList;
    }

    @NonNull
    @Override
    public CategoryIdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_category_food, null);
        CategoryIdHolder categoryIdHolder = new CategoryIdHolder(view);
        return categoryIdHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryIdHolder holder, int position) {
        CategoryId categoryId = categoryIdList.get(position);
        Glide.with(context).load(categoryId.getAvatar()).into(holder.imageCategory);
        Glide.with(context).load(categoryId.getAvatar()).into(holder.imageCategoryBg);
        holder.textCategory.setText(categoryId.getDescription());
    }

    @Override
    public int getItemCount() {
        return categoryIdList.size();
    }

    class CategoryIdHolder extends RecyclerView.ViewHolder {

        ImageView imageCategory,imageCategoryBg;
        TextView textCategory;

        public CategoryIdHolder(@NonNull View itemView) {
            super(itemView);

            imageCategory = itemView.findViewById(R.id.imageCategory);
            imageCategoryBg = itemView.findViewById(R.id.imageCategoryBg);
            textCategory = itemView.findViewById(R.id.textCategory);
        }
    }
}
