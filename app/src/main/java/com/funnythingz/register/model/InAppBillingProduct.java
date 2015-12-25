package com.funnythingz.register.model;

import com.google.gson.annotations.SerializedName;

/**
 * アプリ内課金プロダクト
 */
public class InAppBillingProduct {
    // プロダクトID
    @SerializedName("productId")
    private String mProductId;
    // 商品タイプ
    @SerializedName("type")
    private String mType;
    // 金額
    @SerializedName("price")
    private String mPrice;
    // タイトル
    @SerializedName("title")
    private String mTitle;
    // 概要
    @SerializedName("description")
    private String mDescription;

    public InAppBillingProduct(String productId, String type, String price, String title, String description) {
        mProductId = productId;
        mType = type;
        mPrice = price;
        mTitle = title;
        mDescription = description;
    }

    public String getProductId() {
        return mProductId;
    }

    public String getType() {
        return mType;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }
}
