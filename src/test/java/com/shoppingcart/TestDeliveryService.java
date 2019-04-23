package com.shoppingcart;

import com.shoppingcart.entity.*;
import com.shoppingcart.service.CampaignService;
import com.shoppingcart.service.CartService;
import com.shoppingcart.service.DeliveryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeliveryService {

    private static CartService cartService;
    private static DeliveryService deliveryService;

    @BeforeAll
    static void beforeInstance() {

        cartService = new CartService();
    }

    @BeforeEach
    void initialize() {

        cartService.flush();
        Category food = new Category("1", "food");
        Product apple = new Product("Apple", 100.0, food);
        Product almond = new Product("Almond", 50, food);
        Product banana = new Product("banana", 50, food);

        cartService.addItem(almond, 3);
        cartService.addItem(apple, 2);
        cartService.addItem(banana, 1);
    }

    @Test
    void testCalculateFor() {

        DeliveryService ds = new DeliveryService(10, 8, 2.99);
        ds.calculateFor(Cart.get_instance());
        assertEquals(60.99, cartService.getDeliveryCost());
    }

    @Test
    void testCalculate() {
        DeliveryService ds = new DeliveryService(10, 8, 2.99);
        ds.calculateFor(Cart.get_instance());
        ds.calculate();
        assertEquals(460.99, cartService.getTotalAmountAfterDiscount()+cartService.getDeliveryCost());

    }
}
