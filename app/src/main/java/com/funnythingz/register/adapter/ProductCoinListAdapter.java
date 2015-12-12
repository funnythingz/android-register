package com.funnythingz.register.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.funnythingz.register.R;
import com.funnythingz.register.model.AppCoin;

import java.util.ArrayList;

/**
 */
public class ProductCoinListAdapter extends ArrayAdapter<AppCoin> {

    LayoutInflater mLayoutInflater;

    public ProductCoinListAdapter(Context context, int resource, ArrayList<AppCoin> appCoinList) {
        super(context, resource, appCoinList);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_product_coin_list, parent, false);
        }

        AppCoin appCoin = getItem(position);

        TextView productCoinValueTextView = (TextView) convertView.findViewById(R.id.product_coin_value_text_view);
        productCoinValueTextView.setText(appCoin.getValue());

        Button buyCoinButton = (Button) convertView.findViewById(R.id.buy_coin_button);
        buyCoinButton.setText(appCoin.getPrice().getPriceValue());

        return convertView;
    }
}
