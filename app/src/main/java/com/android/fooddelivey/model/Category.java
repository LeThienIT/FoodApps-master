package com.android.fooddelivey.model;

public class Category {
    private int id;
    private String name;
    private String avatar;
    private int categoryId;

    public Category(int id, String name, String avatar, int categoryId) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.categoryId = categoryId;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
