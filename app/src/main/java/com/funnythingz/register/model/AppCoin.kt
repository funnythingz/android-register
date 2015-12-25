package com.funnythingz.register.model

class AppCoin(val productId: ProductId,
              val productName: String,
              val price: String) {

    fun equals(appCoin: AppCoin): Boolean {
        return productId.id === appCoin.productId.id
    }
}