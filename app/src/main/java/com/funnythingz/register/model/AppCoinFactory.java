package com.funnythingz.register.model;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 */
public class AppCoinFactory {

    ArrayList<String> mResponseList;

    public AppCoinFactory(ArrayList<String> responseList) {
        mResponseList = responseList;
    }

    public ArrayList<AppCoin> createProductCoinList() throws JSONException {

        ArrayList<AppCoin> appCoinList = new ArrayList<AppCoin>();

        InAppBillingProduct inAppBillingProduct;
        for (String response : mResponseList) {
            JSONObject object = new JSONObject(response);
            Gson gson = new Gson();
            inAppBillingProduct = gson.fromJson(String.valueOf(object), InAppBillingProduct.class);
            appCoinList.add(
                    new AppCoin(
                            new ProductId(inAppBillingProduct.getProductId()),
                            inAppBillingProduct.getTitle(),
                            inAppBillingProduct.getPrice()
                    )
            );
        }

        return appCoinList;
    }
}