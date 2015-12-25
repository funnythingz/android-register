package com.funnythingz.register.model

/**
 * アプリ内課金注文情報
 */
class InAppPurchaseData(
        // 注文番号
        val orderId: String,
        // アプリのパッケージ名
        val packageName: String,
        // 商品番号
        val productId: String,
        // 購入日時
        val purchaseTime: String,
        // 購入状態（0：購入 1：キャンセル 2：返金）
        val purchaseState: String,
        // 購入時に指定した任意の補足情報文字列
        val developerPayload: String,
        // 商品とユーザを結びつけるトークン
        val purchaseToken: String)
