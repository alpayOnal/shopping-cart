package com.shoppingcart.service;

import com.shoppingcart.entity.Campaign;
import com.shoppingcart.entity.Cart;
import com.shoppingcart.entity.Item;
import com.shoppingcart.factory.DiscountFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CampaignService implements IService {

    private Cart cart;

    public CampaignService(Cart cart) {
        this.cart = cart;
    }

    public HashMap<String, ArrayList<Campaign>> groupByCategory(Campaign[] campaigns) {

        HashMap<String, ArrayList<Campaign>> campaignList = new HashMap<String, ArrayList<Campaign>>();

        for (Campaign campaign : campaigns) {
            if (campaignList.containsKey(campaign.getCategory().getId())) {
                campaignList.get(campaign.getCategory().getId()).add(campaign);
            } else {
                ArrayList<Campaign> newCampaignList = new ArrayList<Campaign>();
                newCampaignList.add(campaign);
                campaignList.put(campaign.getCategory().getId(), newCampaignList);
            }
        }

        return campaignList;
    }

    public void applyCampaigns(HashMap<String, ArrayList<Campaign>> campaigns) {

        for (String categoryId : campaigns.keySet()) {
            ArrayList<Campaign> campaignList = campaigns.get(categoryId);

            List<Item> itemList = this.cart.getItemList().stream().filter(
                    item -> item.getProduct().getCategory().getId() == categoryId).collect(Collectors.toList());

            this.calculateCampaigns(itemList, campaignList);
        }
    }


    public void calculateCampaigns(List<Item> itemList, ArrayList<Campaign> campaignList) {

        DiscountFactory discountFactory = new DiscountFactory();

        for (Campaign campaign : campaignList) {

            Integer productCount = itemList.stream().filter(item -> item.getQuantity() > 0).mapToInt(item -> item.getQuantity()).sum();

            if (productCount <= campaign.getMinProductCount()) {
                itemList.forEach(item -> item.setCampaignDiscount(0.0));
                continue;
            }


            for (Item item : itemList) {

                double discount = discountFactory.get(campaign.getDiscountType()).calculate(
                        item.getProduct().getPrice(), campaign.getDiscount());

                if (discount > item.getCampaignDiscount())
                    item.setCampaignDiscount(discount);

            }

        }

    }

    public void calculateTotalCampaignDiscount() {

        double totalDiscount = 0.0;
        List<Item> items = this.cart.getItemList();
        for (Item item : items)
            totalDiscount += item.getCampaignDiscount() * item.getQuantity();

        this.cart.setCampaignDiscount(totalDiscount);
    }


    @Override
    public void calculate() {
        this.calculateTotalCampaignDiscount();
        double totalDiscount = this.cart.getTotalAmountAfterDiscount() - this.cart.getCampaignDiscount();
        this.cart.setTotalAmountAfterDiscount(totalDiscount);
    }
}