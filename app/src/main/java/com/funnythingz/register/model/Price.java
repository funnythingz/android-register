package com.funnythingz.register.model;

import static com.funnythingz.register.model.CurrencyUnit.*;

/**
 */
public class Price {

    private int mMoney;
    private CurrencyUnit mCurrencyUnit;

    public Price(int money,  CurrencyUnit currencyUnit) {
        mMoney = money;
        mCurrencyUnit = currencyUnit;
    }

    public String getValue() {
        return Integer.toString(mMoney);
    }

    public int getMoney() {
        return mMoney;
    }

    public String getPriceValue() {
        return getCurrencyUnit() + getValue();
    }

    private String getCurrencyUnit() {
        switch (mCurrencyUnit) {
            case YEN:
                return "￥";
            default:
                return "";
        }
    }
}
