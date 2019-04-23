package com.shoppingcart.entity;

public class Product {
    private String title;
    private double price = 0.0;
    private Category category;

    public Product(String title, Category category) {
        this.title = title;
        this.category = category;
    }

    public Product(String title, double price, Category category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product p = (Product) o ;
        return category.getId() == p.category.getId() && title == p.title;
    }
}
