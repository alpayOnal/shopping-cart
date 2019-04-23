package com.shoppingcart.service;

import com.shoppingcart.entity.Cart;
import com.shoppingcart.entity.Category;
import com.shoppingcart.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class DeliveryService implements IService {

    private double fixedCost = 2.99;

    private double costPerDelivery = 0.0;
    private double costPerProduct = 0.0;
    private Cart cart;

    public DeliveryService(double costPerDelivery, double costPerProduct, double fixedCost) {

        this.fixedCost = fixedCost;
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
    }

    public void calculateFor(Cart cart) {

        this.cart = cart;
        double numberOfProducts = cart.getNumberOfProducts();
        int numberOfDeliveries = this.getCategorySize();
        cart.setDeliveryCost(
                this.costPerDelivery * numberOfDeliveries + this.costPerProduct * numberOfProducts + this.fixedCost);

    }

    public int getCategorySize() {

        List<Item> itemList = this.cart.getItemList();
        ArrayList<Category> categoryList = new ArrayList<Category>();

        for (Item item : itemList) {
            if (!categoryList.contains(item.getProduct().getCategory()))
                categoryList.add(item.getProduct().getCategory());
        }

        return categoryList.size();
    }


    @Override
    public void calculate() {

        double totalAmount = this.cart.getTotalAmountAfterDiscount() + this.cart.getCouponDiscount();
        this.cart.setTotalAmountAfterDiscount(totalAmount);
    }
}
