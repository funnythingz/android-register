package com.funnythingz.register.model

import com.google.gson.Gson

import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

class AppCoinFactory(internal val mResponseList: ArrayList<String>) {

    @Throws(JSONException::class)
    fun createProductCoinList(): ArrayList<AppCoin> {

        val appCoinList = ArrayList<AppCoin>()

        for (response in mResponseList) {
            val jsonObject = JSONObject(response)
            val gson = Gson()
            val inAppBillingProduct: InAppBillingProduct = gson.fromJson(jsonObject.toString(), InAppBillingProduct::class.java)
            appCoinList.add(
                    AppCoin(ProductId(inAppBillingProduct.productId),
                            inAppBillingProduct.title,
                            inAppBillingProduct.price))
        }

        return appCoinList
    }
}