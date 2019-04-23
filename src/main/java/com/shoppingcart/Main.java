package com.shoppingcart;

import com.shoppingcart.entity.*;
import com.shoppingcart.service.CartService;
import com.shoppingcart.service.DeliveryService;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {


        Category food = new Category("1", "food");

        Product apple = new Product("Apple", 100.0, food);
        Product almond = new Product("Almond", 50, food);
        Product banana = new Product("banana", 50, food);

        CartService cartService = new CartService();

        cartService.addItem(almond, 3);
        cartService.addItem(apple, 2);
        cartService.addItem(banana, 1);
//
//        cart.updateItem(apple, 10);
//        cart.deleteItem(apple);
//        cart.flush();

        Campaign discount1 = new Campaign(food, 20.0, 3, DiscountType.Rate);
        Campaign discount2 = new Campaign(food, 50.0, 3, DiscountType.Rate);
        Campaign discount3 = new Campaign(food, 5.0, 3, DiscountType.Amount);

        cartService.applyDiscounts(discount1, discount2, discount3);


        Coupon coupon = new Coupon(100.0, 10.0 ,DiscountType.Rate);
        cartService.applyCoupon(coupon);

        DeliveryService ds = new DeliveryService(10, 8, 2.99);
        ds.calculateFor(Cart.get_instance());


        cartService.getTotalAmountAfterDiscount();
        cartService.getCouponDiscount();
        cartService.getCampaignDiscount();
        cartService.getDeliveryCost();

        cartService.print();
    }
}
