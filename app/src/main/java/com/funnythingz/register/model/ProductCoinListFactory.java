package com.funnythingz.register.model;

import java.util.ArrayList;

/**
 */
public class ProductCoinListFactory {
    public static ArrayList<AppCoin> createProductCoinList() {

        ArrayList<AppCoin> appCoinList = new ArrayList<AppCoin>();

        appCoinList.add(new AppCoin(100, 120));
        appCoinList.add(new AppCoin(200, 240));
        appCoinList.add(new AppCoin(500, 480));
        appCoinList.add(new AppCoin(800, 720));
        appCoinList.add(new AppCoin(1600, 1200));

        return appCoinList;
    }
}
