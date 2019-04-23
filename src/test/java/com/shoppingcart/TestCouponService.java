package com.shoppingcart;

import com.shoppingcart.entity.*;
import com.shoppingcart.service.CartService;
import com.shoppingcart.service.CouponService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCouponService {

    private static CartService cartService;
    private static CouponService couponService;

    @BeforeAll
    static void beforeInstance() {

        cartService = new CartService();
        couponService = new CouponService(Cart.get_instance());
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
    void testApplyCoupon() {

        Coupon coupon = new Coupon(100.0, 10.0, DiscountType.Rate);
        cartService.applyCoupon(coupon);
        assertEquals(40.0, cartService.getCouponDiscount());
    }

    @Test
    void testCalculate() {

        Coupon coupon = new Coupon(100.0, 10.0, DiscountType.Rate);
        cartService.applyCoupon(coupon);
        Double totalDiscount = cartService.getTotalAmountAfterDiscount() - cartService.getCouponDiscount();
        couponService.calculate();
        assertEquals(totalDiscount, cartService.getTotalAmountAfterDiscount());

    }
}
