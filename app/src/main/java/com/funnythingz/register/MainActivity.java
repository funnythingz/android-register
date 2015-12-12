package com.funnythingz.register;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.funnythingz.register.adapter.ProductCoinListAdapter;
import com.funnythingz.register.model.ProductCoin;
import com.funnythingz.register.model.ProductCoinListFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProductCoinListAdapter mProductCoinListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myCoinValueTextView = (TextView) findViewById(R.id.my_coin_value_text_view);
        myCoinValueTextView.setText("0");

        ArrayList<ProductCoin> productCoinList = ProductCoinListFactory.createProductCoinList();
        mProductCoinListAdapter = new ProductCoinListAdapter(getApplicationContext(), R.layout.adapter_product_coin_list, productCoinList);

        ListView listView = (ListView) findViewById(R.id.product_coin_list_view);
        listView.setAdapter(mProductCoinListAdapter);
    }
}
