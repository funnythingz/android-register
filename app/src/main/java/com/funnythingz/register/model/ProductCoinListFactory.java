package com.funnythingz.register.model;

import java.util.ArrayList;

/**
 */
public class ProductCoinListFactory {
    public static ArrayList<ProductCoin> createProductCoinList() {

        ArrayList<ProductCoin> productCoinList = new ArrayList<ProductCoin>();

        productCoinList.add(new ProductCoin(100, 120));
        productCoinList.add(new ProductCoin(200, 240));
        productCoinList.add(new ProductCoin(500, 480));
        productCoinList.add(new ProductCoin(800, 720));
        productCoinList.add(new ProductCoin(1600, 1200));

        return productCoinList;
    }
}
