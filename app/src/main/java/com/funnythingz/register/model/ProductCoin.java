package com.funnythingz.register.model;

/**
 */
public class ProductCoin {

    private int mValue;

    public ProductCoin(int value) {
        mValue = value;
    }

    public String getValue() {
        return Integer.toString(mValue);
    }

    public int getCoin() {
        return mValue;
    }
}