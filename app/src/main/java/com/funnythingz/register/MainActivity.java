package com.funnythingz.register;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.android.vending.billing.IInAppBillingService;
import com.funnythingz.register.adapter.ProductCoinListAdapter;
import com.funnythingz.register.model.AppCoin;
import com.funnythingz.register.model.AppCoinFactory;

import org.json.JSONException;

import java.util.ArrayList;

import static com.funnythingz.register.model.BillingResponseResult.*;

public class MainActivity extends AppCompatActivity {

    private ProductCoinListAdapter mProductCoinListAdapter;
    private Handler mHandler = new Handler();

    private IInAppBillingService mService;
    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name,
                                       IBinder service) {
            mService = IInAppBillingService.Stub.asInterface(service);

            // 商品一覧
            productListView();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 課金サービスに接続
        connectIInAppBillingService();

        // 保有コイン
        myAppCoinView();
    }

    private void connectIInAppBillingService() {
        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
    }

    // TODO: ユーザーの保有コインを表示
    private void myAppCoinView() {
        // TODO: APIと通信して保有コインを表示する

        // FIXME: とりあえずダミー
        TextView myCoinTextView = (TextView) findViewById(R.id.my_coin_value_text_view);
        myCoinTextView.setText("0");
    }

    // TODO: 商品情報を取得
    private void productListView() {
        ArrayList<String> requestProductIdList = new ArrayList<>();
        // TODO: Sandboxのアイテムだけ問い合わせる
        requestProductIdList.add("android.test.purchased");
        requestProductIdList.add("android.test.canceled");
        requestProductIdList.add("android.test.refunded");
        requestProductIdList.add("android.test.item_unavailable");

        Bundle query = new Bundle();
        query.putStringArrayList("ITEM_ID_LIST", requestProductIdList);
        try {
            // リクエストに対するレスポンスを取得する
            Bundle details = mService.getSkuDetails(3, getPackageName(), "inapp", query);

            if (details == null) {
                return;
            }

            // レスポンスコードを取得
            int responseCode = details.getInt("RESPONSE_CODE");

            // 成功した時
            if (OK.equals(responseCode)) {
                // FIXME: 商品リストを取得 (順番はa-z順っぽいので並び替えが必要)
                ArrayList<String> responseList = details.getStringArrayList("DETAILS_LIST");
                AppCoinFactory appCoinFactory = new AppCoinFactory(responseList);

                ArrayList<AppCoin> productCoinList;
                try {
                    // レスポンスから商品一覧を取得してViewにはめる
                    productCoinList = appCoinFactory.createProductCoinList();
                    mProductCoinListAdapter = new ProductCoinListAdapter(this, R.layout.adapter_product_coin_list_raw, productCoinList, mService);
                    ListView listView = (ListView) findViewById(R.id.product_coin_list_view);
                    listView.setAdapter(mProductCoinListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
