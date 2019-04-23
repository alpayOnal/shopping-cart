package com.shoppingcart;

import com.shoppingcart.entity.*;
import com.shoppingcart.service.CampaignService;
import com.shoppingcart.service.CouponService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.shoppingcart.service.CartService;
import com.shoppingcart.service.DeliveryService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCartService {

    private static CartService cartService;
    private static Cart cart1;
    private static Cart cart2;

    @BeforeAll
    static void beforeInstance() {
        cartService = new CartService();
    }

    @BeforeEach
    void initialize() {
        cartService.flush();
        cart1 = Cart.get_instance();
        cart2 = Cart.get_instance();

        Category food = new Category("1", "food");
        Product apple = new Product("Apple", 100.0, food);
        Product almond = new Product("Almond", 50, food);
        Product banana = new Product("banana", 50, food);

        cartService.addItem(almond, 3);
        cartService.addItem(apple, 2);
        cartService.addItem(banana, 1);

    }


    @Test
    void testSingleCartInstance() {

        cart1 = Cart.get_instance();
        cart2 = Cart.get_instance();
        assertEquals(cart1, cart2);
    }

    @Test
    void testCartGetItemList() {
        assertEquals(cart1.getItemList().size(), cart2.getItemList().size());
    }

    @Test
    void testCheckProducts() {
        List<Item> itemList1 = cart1.getItemList();
        List<Item> itemList2 = cart2.getItemList();

        assertEquals(true, itemList1.get(0).getProduct().equals(itemList2.get(0).getProduct()));
        assertEquals(true, itemList1.get(1).getProduct().equals(itemList2.get(1).getProduct()));

    }

    @Test
    void testCheckAdditem() {
        Category food = new Category("1", "food");
        Product banana = new Product("Banana", 100.0, food);
        cartService.addItem(banana, 3);
        assertEquals(9, cartService.getNumberOfProducts());
    }

    @Test
    void testSetNumberOfProducts() {
        Category food = new Category("1", "food");
        Product banana = new Product("Banana", 50, food);
        cartService.addItem(banana, 5);
        assertEquals(11, cartService.getNumberOfProducts());
    }

    @Test
    void testDeleteItem() {
        Category food = new Category("1", "food");
        Product apple = new Product("Apple", food);
        cartService.deleteItem(apple);
        assertEquals(4, cartService.getNumberOfProducts());
    }

    @Test
    void testUpdateItem() {
        Category food = new Category("1", "food");
        Product apple = new Product("Apple", food);
        cartService.updateItem(apple, 8);
        Item item = cartService.findItem(apple);
        assertEquals(8, item.getQuantity());
    }

    @Test
    void testFindItem() {
        Category food = new Category("1", "food");
        Product apple = new Product("Apple", food);
        Item item = cartService.findItem(apple);
        assertEquals(true, item.getProduct().equals(apple));

        Product apple1 = new Product("Apple1", food);
        assertEquals(false, item.getProduct().equals(apple1));

    }

    @Test
    void testGetNumberOfProducts() {
        assertEquals(6, cartService.getNumberOfProducts());
    }

    @Test
    void testGetTotalAmount() {
        assertEquals(400, cartService.getTotalAmount());
    }

    @Test
    void testGetDeliveryCost() {
        DeliveryService ds = new DeliveryService(10, 8, 2.0);
        ds.calculateFor(Cart.get_instance());
        assertEquals(60.0, cartService.getDeliveryCost());
    }

    @Test
    void testGetCampaignDiscount() {
        CampaignService campaignService = new CampaignService(Cart.get_instance());
        cartService.calculate();
        assertEquals(0.0, cartService.getCampaignDiscount());
    }

    @Test
    void testGetCouponDiscount() {
        Coupon coupon = new Coupon(500.0, 10.0, DiscountType.Rate);
        cartService.applyCoupon(coupon);

        CouponService couponService = new CouponService(Cart.get_instance());
        couponService.calculate();
        assertEquals(0.0, cartService.getCouponDiscount());
    }
}