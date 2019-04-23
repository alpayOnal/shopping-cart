package com.shoppingcart.factory;


import com.shoppingcart.entity.DiscountType;
import com.shoppingcart.entity.IDiscountType;

import java.util.ArrayList;
import java.util.Arrays;

public class DiscountFactory {

    private ArrayList<String> availableTypes = new ArrayList<String>(Arrays.asList(DiscountType.Rate, DiscountType.Amount));
//    private HashMap<String, IDiscountType> = new HashMap<String, IDiscountType>();

    public IDiscountType get(String discountType) {

        if (discountType == DiscountType.Rate)
            return new DiscountTypeRate();

        if (discountType == DiscountType.Amount)
            return new DiscountTypeAmount();

        //   todo com.shoppingcart.exception throw
        return null;
    }

}


class DiscountTypeRate implements IDiscountType {

    @Override
    public double calculate(double price, double discount) {
        return price * discount / 100;
    }
}


class DiscountTypeAmount implements IDiscountType {

    @Override
    public double calculate(double price, double discount) {
        return discount;
    }
}
