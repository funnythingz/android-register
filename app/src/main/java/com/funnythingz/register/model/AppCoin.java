package com.funnythingz.register.model;

/**
 */
public class AppCoin {

    private ProductId mProductId;
    private String mProductName;
    private String mPrice;

    public AppCoin(ProductId productId, String productName, String price) {
        mProductId = productId;
        mProductName = productName;
        mPrice = price;
    }

    public ProductId getProductId() {
        return mProductId;
    }

    public String getProductName() {
        return mProductName;
    }

    public String getPrice() {
        return mPrice;
    }

    public boolean equals(AppCoin appCoin) {
        return mProductId.getId() == appCoin.getProductId().getId();
    }
}