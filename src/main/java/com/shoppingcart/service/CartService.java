package com.shoppingcart.service;

import com.shoppingcart.entity.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartService implements IService{


    private Cart cart;
    private CampaignService campaignService;
    private CouponService couponService;


    public CartService() {

        this.cart = Cart.get_instance();
        this.campaignService = new CampaignService(cart);
        this.couponService = new CouponService(cart);

    }

    public void addItem(Product product, Integer quantity) {

        Item newItem = new Item(product, quantity);
        Item existItem = this.findItem(product);

        if (existItem != null)
            existItem.setQuantity(existItem.getQuantity() + quantity);
        else
            this.cart.getItemList().add(newItem);

        this.calculate();
    }

    public void deleteItem(Product product) {

        Item existItem = this.findItem(product);

        if (existItem != null)
            this.cart.getItemList().remove(existItem);

        this.calculate();
    }

    public void updateItem(Product product, Integer quantity) {

        Item existItem = this.findItem(product);

        if (existItem != null)
            existItem.setQuantity(quantity);
        else
            return;

        this.calculate();
    }

    public Item findItem(Product product) {

        Item existItem = this.cart.getItemList().stream().filter(
                item -> item.getProduct().equals(product)).findFirst().orElse(null);

        if (existItem != null)
            return existItem;

        return null;
    }

    public void flush() {

        this.cart.getItemList().clear();
        this.calculate();
    }


    public void calculate() {

        this.calculateTotalAmount();
        this.calculateNumberOfProducts();
    }

    public void applyDiscounts(Campaign... campaigns) {

        HashMap<String, ArrayList<Campaign>> campaignList;
        campaignList = this.campaignService.groupByCategory(campaigns);
        this.campaignService.applyCampaigns(campaignList);
        this.campaignService.calculate();
    }

    public void applyCoupon(Coupon coupon) {
        this.couponService.applyCoupon(coupon);
        this.couponService.calculate();
    }

    private void calculateTotalAmount() {

        double totalAmount = 0.0;
        List<Item> items = this.cart.getItemList();
        for (Item item : items)
            totalAmount += item.getProduct().getPrice() * item.getQuantity();

        this.cart.setTotalAmount(totalAmount);
        this.cart.setTotalAmountAfterDiscount(totalAmount);
    }

    private void calculateNumberOfProducts() {

        List<Item> itemList = this.cart.getItemList();
        Integer numberOfProducts = itemList.stream().filter(
                item -> item.getQuantity() > 0).mapToInt(item -> item.getQuantity()).sum();
        this.cart.setNumberOfProducts(numberOfProducts);
    }

    public double getTotalAmountAfterDiscount() {

        return this.cart.getTotalAmountAfterDiscount();
    }

    public double getCouponDiscount() {

        return this.cart.getCouponDiscount();
    }

    public double getCampaignDiscount() {

        return this.cart.getCampaignDiscount();
    }

    public double getDeliveryCost() {

        return this.cart.getDeliveryCost();
    }

    public double getTotalAmount() {

        return this.cart.getTotalAmount();
    }

    public double getNumberOfProducts() {

        return this.cart.getNumberOfProducts();
    }

    public void print() {

        System.out.format("\n|%1$-44s %2$-23s %3$-44s|\n\n", "", "----------   CART INFO   ----------", "");
        this.printItems();
        System.out.format("\n\n%1$-30s%2$-20s\n", "Total  ", ":" + this.getTotalAmount());
        System.out.format("%1$-30s%2$-20s\n", "Total Campaign Discount", ":" + this.getCampaignDiscount());
        System.out.format("%1$-30s%2$-20s\n", "Total Coupon Discount ", ":" + this.getCouponDiscount());
        System.out.format("%1$-30s%2$-20s\n", "Total Delivery Cost ", ":" + this.getDeliveryCost());
        Double totalAmount = this.getTotalAmountAfterDiscount()+this.getDeliveryCost();
        System.out.format("%1$-30s%2$-20s\n", "Total Price ", ":" + totalAmount);
    }

    public void printItems() {

        StringBuilder stringBuilder = new StringBuilder();
        String line = new String(new char[18]).replace("\0", "-");

        String format = "|%1$-20s %2$-20s %3$-20s %4$-20s %5$-20s %6$-20s|\n";
        System.out.format(format, line, line, line, line, line, line);

        System.out.format(format,
                "Category Name",
                "Product Name",
                "Quantity",
                "Unit Price",
                "Total Price",
                "Total Discount");

        System.out.format(format, line, line, line, line, line, line);

        HashMap<String, ArrayList<Item>> categories = this.getItemsByGroupByCategory();

        for (String categoryId : categories.keySet()) {
            ArrayList<Item> itemList = categories.get(categoryId);

            for (Item item : itemList) {
                System.out.format(format,
                        item.getProduct().getCategory().getTitle(),
                        item.getProduct().getTitle(),
                        item.getQuantity(),
                        item.getProduct().getPrice(),
                        item.getProduct().getPrice() * item.getQuantity(),
                        item.getCampaignDiscount() * item.getQuantity()
                );

            }
        }
    }


    public HashMap<String, ArrayList<Item>> getItemsByGroupByCategory() {

        HashMap<String, ArrayList<Item>> categories = new HashMap<String, ArrayList<Item>>();
        List<Item> itemList = this.cart.getItemList();

        for (Item item : itemList) {
            Product product = item.getProduct();
            if (categories.containsKey(product.getCategory().getId())) {
                categories.get(product.getCategory().getId()).add(item);
            } else {
                ArrayList<Item> newItemList = new ArrayList<Item>();
                newItemList.add(item);
                categories.put(product.getCategory().getId(), newItemList);
            }
        }
        return categories;
    }

}
