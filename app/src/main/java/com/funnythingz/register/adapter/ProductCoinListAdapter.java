package com.funnythingz.register.adapter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.funnythingz.register.R;
import com.funnythingz.register.model.AppCoin;

import java.util.ArrayList;

import static com.funnythingz.register.model.BillingResponseResult.*;

/**
 */
public class ProductCoinListAdapter extends ArrayAdapter<AppCoin> {

    LayoutInflater mLayoutInflater;
    private IInAppBillingService billingService;
    private Activity mMainActivity;
    private Handler mHandler = new Handler();

    public ProductCoinListAdapter(Activity activity, int resource, ArrayList<AppCoin> appCoinList, IInAppBillingService inAppBillingService) {
        super(activity, resource, appCoinList);
        mLayoutInflater = LayoutInflater.from(activity);
        billingService = inAppBillingService;
        mMainActivity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_product_coin_list_raw, parent, false);
        }

        final AppCoin appCoin = getItem(position);

        ViewHolder.productNameText = (TextView) convertView.findViewById(R.id.product_name_text_view);
        ViewHolder.productIdText = (TextView) convertView.findViewById(R.id.product_id_text_view);
        ViewHolder.buyButton = (Button) convertView.findViewById(R.id.buy_coin_button);

        ViewHolder.productNameText.setText(appCoin.getProductName());
        ViewHolder.productIdText.setText(appCoin.getProductId().getId());
        ViewHolder.buyButton.setText(appCoin.getPrice());

        ViewHolder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle buyIntentBundle = null;
                // 購入リクエストを送信
                try {
                    // TODO: `developerPayload` はユニークな値をAndroid側で生成して渡す
                    buyIntentBundle = billingService.getBuyIntent(3, mMainActivity.getPackageName(), appCoin.getProductId().getId(), "inapp", "developerPayload");

                    // 購入が可能であれば
                    if (OK.equals(buyIntentBundle.getInt("RESPONSE_CODE"))) {
                        // 購入フローを開始
                        PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
                        // 購入フローを完了する
                        try {
                            mMainActivity.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                    }

                    // FIXME: コイン購入の際にコインを付与して消費処理を行うので本来はここは通らないはず
                    if (ITEM_ALREADY_OWNED.equals(buyIntentBundle.getInt("RESPONSE_CODE"))) {
                        Toast.makeText(mMainActivity, "既に所有しているので購入できない", Toast.LENGTH_SHORT).show();

                        // Warinig: consumePurchase はメインスレッドから呼び出さない事。このメソッドはネットワークリクエストのトリガーになり、メインスレッドをブロックする
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        // TODO: 消費処理
                                        int response = 0;
                                        try {
                                            // TODO: とりあえずSandbox的な処理
                                            response = billingService.consumePurchase(3, mMainActivity.getPackageName(), "android.test.purchased");

                                            if (OK.equals(response)) {
                                                Toast.makeText(mMainActivity, "購入した商品を消費しました", Toast.LENGTH_SHORT).show();
                                            }

                                            if (ITEM_NOT_OWNED.equals(response)) {
                                                Toast.makeText(mMainActivity, "所有してない商品なのに消費しようとして失敗", Toast.LENGTH_SHORT).show();
                                            }

                                        } catch (RemoteException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        public static TextView productNameText;
        public static TextView productIdText;
        public static Button buyButton;
    }
}
