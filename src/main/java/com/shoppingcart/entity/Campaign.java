package com.shoppingcart.entity;

public class Campaign {

    private Category category;
    private double discount;
    private Integer minProductCount;
    private String discountType;

    public Campaign(Category category, double discount, Integer minProductCount, String discountType) {
        this.category = category;
        this.discount = discount;
        this.minProductCount = minProductCount;
        this.discountType = discountType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getMinProductCount() {
        return minProductCount;
    }

    public void setMinProductCount(Integer minProductCount) {
        this.minProductCount = minProductCount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
}
