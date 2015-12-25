package com.funnythingz.register.model

import com.google.gson.annotations.SerializedName

/**
 * アプリ内課金プロダクト
 */
class InAppBillingProduct(
        // プロダクトID
        val productId: String,
        // 商品タイプ
        val type: String,
        // 金額
        val price: String,
        // タイトル
        val title: String,
        // 概要
        val description: String)
