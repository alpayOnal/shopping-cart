package com.shoppingcart.entity;

public class Coupon {

    private double minimumCartAmount = 0.0;
    private double discount = 0.0;
    private String discountType;


    public Coupon(double minimumCartAmount, double discount, String discountType) {
        this.minimumCartAmount = minimumCartAmount;
        this.discount = discount;
        this.discountType = discountType;
    }

    public double getMinimumCartAmount() {
        return minimumCartAmount;
    }

    public void setMinimumCartAmount(double minimumCartAmount) {
        this.minimumCartAmount = minimumCartAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
}
