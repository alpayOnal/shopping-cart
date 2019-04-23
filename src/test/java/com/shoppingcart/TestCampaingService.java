package com.shoppingcart;

import com.shoppingcart.entity.*;
import com.shoppingcart.service.CampaignService;
import com.shoppingcart.service.CartService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCampaingService {

    private static CartService cartService;
    private static CampaignService campaignService;

    @BeforeAll
    static void beforeInstance() {

        cartService = new CartService();
        campaignService = new CampaignService(Cart.get_instance());
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
    void testCouponDiscount() {

        Category food = new Category("1", "food");
        Campaign discount1 = new Campaign(food, 20.0, 3, DiscountType.Rate);
        Campaign discount2 = new Campaign(food, 50.0, 3, DiscountType.Rate);
        Campaign discount3 = new Campaign(food, 5.0, 3, DiscountType.Amount);

        cartService.applyDiscounts(discount1, discount2, discount3);
        assertEquals(200.0, cartService.getCampaignDiscount());
    }

    @Test
    void testCalculate() {

        Category food = new Category("1", "food");
        Campaign discount1 = new Campaign(food, 20.0, 3, DiscountType.Rate);
        Campaign discount2 = new Campaign(food, 50.0, 3, DiscountType.Rate);
        Campaign discount3 = new Campaign(food, 5.0, 3, DiscountType.Amount);
        cartService.applyDiscounts(discount1, discount2, discount3);

        double totalDiscount = cartService.getTotalAmountAfterDiscount() - cartService.getCampaignDiscount();
        campaignService.calculate();
        assertEquals(totalDiscount, cartService.getTotalAmountAfterDiscount());

    }
}
