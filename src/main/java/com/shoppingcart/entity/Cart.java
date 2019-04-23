package com.shoppingcart.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private double totalDiscount = 0.0;
    private double deliveryCost = 0.0;
    private double campaignDiscount = 0.0;
    private double couponDiscount = 0.0;
    private double totalAmount = 0.0;
    private double totalAmountAfterDiscount = 0.0;
    private double numberOfProducts = 0.0;


    private List<Item> itemList = new ArrayList<Item>();

    private static Cart instance = null;

    public static Cart get_instance() {
        if (instance == null)
            instance = new Cart();

        return instance;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }


    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public void setCampaignDiscount(double campaignDiscount) {
        this.campaignDiscount = campaignDiscount;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmountAfterDiscount() {
        return totalAmountAfterDiscount;
    }

    public void setTotalAmountAfterDiscount(double totalAmountAfterDiscount) {
        this.totalAmountAfterDiscount = totalAmountAfterDiscount;
    }

    public double getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(double numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
}
