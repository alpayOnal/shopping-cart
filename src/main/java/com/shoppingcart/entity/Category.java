package com.shoppingcart.entity;

public class Category {

    private String id;
    private Category parentCatgory;
    private String title;

    public Category(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Category(String title) {
        this.title = title;
    }

    public Category getParentCatgory() {
        return parentCatgory;
    }

    public void setParentCatgory(Category parentCatgory) {
        this.parentCatgory = parentCatgory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
