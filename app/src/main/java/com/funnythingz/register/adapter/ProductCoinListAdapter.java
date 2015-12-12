package com.funnythingz.register.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.funnythingz.register.R;
import com.funnythingz.register.model.ProductCoin;

import java.util.ArrayList;

/**
 */
public class ProductCoinListAdapter extends ArrayAdapter<ProductCoin> {

    LayoutInflater mLayoutInflater;

    public ProductCoinListAdapter(Context context, int resource, ArrayList<ProductCoin> productCoinList) {
        super(context, resource, productCoinList);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_product_coin_list, parent, false);
        }

        ProductCoin productCoin = getItem(position);

        TextView productCoinValueTextView = (TextView) convertView.findViewById(R.id.product_coin_value_text_view);
        productCoinValueTextView.setText(productCoin.getValue());

        Button buyCoinButton = (Button) convertView.findViewById(R.id.buy_coin_button);
        buyCoinButton.setText(productCoin.getPrice().getPriceValue());

        return convertView;
    }
}
