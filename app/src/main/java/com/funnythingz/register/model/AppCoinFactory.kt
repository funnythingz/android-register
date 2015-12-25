package com.funnythingz.register.model

import com.google.gson.Gson

import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

class AppCoinFactory(internal val mResponseList: ArrayList<String>) {

    @Throws(JSONException::class)
    fun createProductCoinList(): ArrayList<AppCoin> {

       return mResponseList.map {
            val jsonObject = JSONObject(it)
            val gson = Gson()
            val inAppBillingProduct: InAppBillingProduct = gson.fromJson(jsonObject.toString(), InAppBillingProduct::class.java)
            AppCoin(ProductId(inAppBillingProduct.productId),
                    inAppBillingProduct.title,
                    inAppBillingProduct.price)
        } as ArrayList<AppCoin>
    }
}