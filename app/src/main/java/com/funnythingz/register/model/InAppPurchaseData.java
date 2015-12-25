package com.funnythingz.register.model;

import com.google.gson.annotations.SerializedName;

/**
 * アプリ内課金注文情報
 */
public class InAppPurchaseData {
    // 注文番号
    @SerializedName("orderId")
    private String mOrderId;
    // アプリのパッケージ名
    @SerializedName("packageName")
    private String mPackageName;
    // 商品番号
    @SerializedName("productId")
    private String mProductId;
    // 購入日時
    @SerializedName("purchaseTime")
    private String mPurchaseTime;
    // 購入状態（0：購入 1：キャンセル 2：返金）
    @SerializedName("purchaseState")
    private String mPurchaseState;
    // 購入時に指定した任意の補足情報文字列
    @SerializedName("developerPayload")
    private String mDeveloperPayload;
    // 商品とユーザを結びつけるトークン
    @SerializedName("purchaseToken")
    private String mPurchaseToken;

    public InAppPurchaseData(String orderId, String packageName, String productId, String purchaseTime, String purchaseState, String developerPayload, String purchaseToken) {
        mOrderId = orderId;
        mPackageName = packageName;
        mProductId = productId;
        mPurchaseTime = purchaseTime;
        mPurchaseState = purchaseState;
        mDeveloperPayload = developerPayload;
        mPurchaseToken = purchaseToken;
    }

    public String getOrderId() {
        return mOrderId;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public String getProductId() {
        return mProductId;
    }

    public String getPurchaseTime() {
        return mPurchaseTime;
    }

    public String getPurchaseState() {
        return mPurchaseState;
    }

    public String getDeveloperPayload() {
        return mDeveloperPayload;
    }

    public String getPurchaseToken() {
        return mPurchaseToken;
    }
}
