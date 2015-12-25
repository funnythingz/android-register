package com.funnythingz.register.adapter

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.android.vending.billing.IInAppBillingService
import com.funnythingz.register.R
import com.funnythingz.register.model.AppCoin

import java.util.ArrayList

import com.funnythingz.register.model.BillingResponseResult.*

class ProductCoinListAdapter(private val mMainActivity: Activity,
                             private val billingService: IInAppBillingService,
                             resource: Int,
                             appCoinList: ArrayList<AppCoin>) : ArrayAdapter<AppCoin>(mMainActivity, resource, appCoinList) {

    internal var mLayoutInflater: LayoutInflater
    private val mHandler = Handler()

    init {
        mLayoutInflater = LayoutInflater.from(mMainActivity)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val appCoin = getItem(position)
        var view: View? = convertView

        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.adapter_product_coin_list_raw, parent, false)
        }

        val productNameText: TextView = view?.findViewById(R.id.product_name_text_view) as TextView
        val productIdText: TextView = view?.findViewById(R.id.product_id_text_view) as TextView
        val buyButton: Button = view?.findViewById(R.id.buy_coin_button) as Button

        productNameText.text = appCoin.productName
        productIdText.text = appCoin.productId.id
        buyButton.text = appCoin.price

        buyButton.setOnClickListener {
            // 購入リクエストを送信
            // TODO: `developerPayload` はユニークな値をAndroid側で生成して渡す
            var buyIntentBundle: Bundle? = billingService.getBuyIntent(3, mMainActivity.packageName, appCoin.productId.id, "inapp", "developerPayload")

            // 購入が可能であれば
            if (OK.equals(buyIntentBundle!!.getInt("RESPONSE_CODE"))) {
                // 購入フローを開始
                val pendingIntent = buyIntentBundle.getParcelable<PendingIntent>("BUY_INTENT")
                // 購入フローを完了する
                try {
                    mMainActivity.startIntentSenderForResult(pendingIntent.intentSender, 1001, Intent(), Integer.valueOf(0)!!, Integer.valueOf(0)!!, Integer.valueOf(0)!!)
                } catch (e: IntentSender.SendIntentException) {
                    e.printStackTrace()
                }

            }

            // FIXME: コイン購入の際にコインを付与して消費処理を行うので本来はここは通らないはず
            if (ITEM_ALREADY_OWNED.equals(buyIntentBundle.getInt("RESPONSE_CODE"))) {
                Toast.makeText(mMainActivity, "既に所有しているので購入できない", Toast.LENGTH_SHORT).show()

                // Warinig: consumePurchase はメインスレッドから呼び出さない事。このメソッドはネットワークリクエストのトリガーになり、メインスレッドをブロックする
                Thread(Runnable {
                    mHandler.post {
                        // TODO: 消費処理
                        // TODO: とりあえずSandbox的な処理
                        var response: Int = billingService.consumePurchase(3, mMainActivity.packageName, "android.test.purchased")

                        if (OK.equals(response)) {
                            Toast.makeText(mMainActivity, "購入した商品を消費しました", Toast.LENGTH_SHORT).show()
                        }

                        if (ITEM_NOT_OWNED.equals(response)) {
                            Toast.makeText(mMainActivity, "所有してない商品なのに消費しようとして失敗", Toast.LENGTH_SHORT).show()
                        }
                    }
                }).start()
            }
        }

        return view!!
    }
}
