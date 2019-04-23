package com.shoppingcart.entity;


public class Item {

    private Product product;
    private Integer quantity;
    private double campaignDiscount = 0.0;


    public Item(Product product, Integer quantity) {
        this.setProduct(product);
        this.setQuantity(quantity);
    }

    public Item(Product product) {
        this.setProduct(product);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public void setCampaignDiscount(double campaignDiscount) {
        this.campaignDiscount = campaignDiscount;
    }
}
