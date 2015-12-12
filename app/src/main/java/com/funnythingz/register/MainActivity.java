package com.funnythingz.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.funnythingz.register.adapter.ProductCoinListAdapter;
import com.funnythingz.register.model.AppCoin;
import com.funnythingz.register.model.ProductCoinListFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProductCoinListAdapter mProductCoinListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myCoinValueTextView = (TextView) findViewById(R.id.my_coin_value_text_view);

        // TODO: エンティティのもってる保有コインを取得
        myCoinValueTextView.setText("0");

        ArrayList<AppCoin> appCoinList = ProductCoinListFactory.createProductCoinList();
        mProductCoinListAdapter = new ProductCoinListAdapter(getApplicationContext(), R.layout.adapter_product_coin_list, appCoinList);

        ListView listView = (ListView) findViewById(R.id.product_coin_list_view);
        listView.setAdapter(mProductCoinListAdapter);
    }
}
