package com.android.fooddelivey.model;

public class CategoryId {
    private int id;
    private String name;
    private String avatar;
    private int categoryId;
    private int cateid;
    private String description;

    public CategoryId(int id, String name, String avatar, int categoryId, int cateid, String description) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.categoryId = categoryId;
        this.cateid = cateid;
        this.description = description;
    }

    public CategoryId() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }
}
