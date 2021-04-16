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
import com.android.fooddelivey.model.Category;
import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{

    Context context;
    List<Category> categoryList;
    private final OnCateListener mOnCateListener;
    String tag;

    public CategoryAdapter(Context context, List<Category> categoryList, OnCateListener mOnCateListener, String tag) {
        this.context = context;
        this.categoryList = categoryList;
        this.mOnCateListener = mOnCateListener;
        this.tag = tag;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_category, null);
        CategoryHolder categoryHolder = new CategoryHolder(view, mOnCateListener);
        return categoryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryHolder holder, int position) {
        Category category = categoryList.get(position);
        Glide.with(context).load(category.getAvatar()).into(holder.categoryImg);
        holder.categoryName.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCateListener.onCateClick(holder.getAdapterPosition(), tag);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView categoryImg;
        TextView categoryName;
        OnCateListener onCateListener;

        public CategoryHolder(@NonNull View itemView, OnCateListener onCateListener) {
            super(itemView);
            categoryImg = itemView.findViewById(R.id.categoryImg);
            categoryName = itemView.findViewById(R.id.categoryName);

            this.onCateListener = onCateListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCateListener.onCateClick(getAdapterPosition(), tag);
        }
    }

    public interface OnCateListener {
        void onCateClick(int position, String tag);
    }
}
