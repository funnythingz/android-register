package com.funnythingz.register.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.funnythingz.register.R;
import com.funnythingz.register.model.ProductCoin;

import java.util.ArrayList;

/**
 */
public class ProductCoinListAdapter extends ArrayAdapter<ProductCoin> {

    public ProductCoinListAdapter(Context context, int resource, ArrayList<ProductCoin> productCoinList) {
        super(context, resource, productCoinList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = ((Activity) getContext()).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.adapter_product_coin_list, parent, false);
            ProductCoin productCoin = getItem(position);
            // TODO: viewにProductCoinをbindする
        }

        return view;
    }
}
