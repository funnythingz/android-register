package com.funnythingz.register.model;

import java.util.ArrayList;

/**
 */
public class ProductCoinListFactory {
    public static ArrayList<ProductCoin> createProductCoinList() {

        ArrayList<ProductCoin> productCoinList = new ArrayList<ProductCoin>();

        productCoinList.add(new ProductCoin(100));
        productCoinList.add(new ProductCoin(200));
        productCoinList.add(new ProductCoin(500));
        productCoinList.add(new ProductCoin(800));
        productCoinList.add(new ProductCoin(1600));

        return productCoinList;
    }
}
