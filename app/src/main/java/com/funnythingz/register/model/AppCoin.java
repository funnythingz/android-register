package com.funnythingz.register.model;

/**
 */
public class AppCoin {

    private int mCoin;
    private Price mPrice;

    public AppCoin(int coin, int price) {
        mCoin = coin;
        mPrice = new Price(price, CurrencyUnit.YEN);
    }

    public String getValue() {
        return Integer.toString(mCoin);
    }

    public int getCoin() {
        return mCoin;
    }

    public Price getPrice() {
        return mPrice;
    }
}