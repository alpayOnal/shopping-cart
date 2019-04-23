package com.shoppingcart.service;

import com.shoppingcart.entity.Cart;
import com.shoppingcart.entity.Coupon;
import com.shoppingcart.factory.DiscountFactory;

public class CouponService implements IService {

    private Cart cart;

    public CouponService(Cart cart) {
        this.cart = cart;
    }

    public void applyCoupon(Coupon coupon) {

        if (this.cart.getTotalAmount() > coupon.getMinimumCartAmount()) {
            DiscountFactory discountFactory = new DiscountFactory();
            double discountCoupon = discountFactory.get(coupon.getDiscountType()).calculate(
                    this.cart.getTotalAmountAfterDiscount(), coupon.getDiscount());
            this.cart.setCouponDiscount(discountCoupon);
        }else{
            this.cart.setCouponDiscount(0.0);
        }

    }


    @Override
    public void calculate() {
        double totalDiscount = this.cart.getTotalAmountAfterDiscount() - this.cart.getCouponDiscount();
        this.cart.setTotalAmountAfterDiscount(totalDiscount);
    }
}
