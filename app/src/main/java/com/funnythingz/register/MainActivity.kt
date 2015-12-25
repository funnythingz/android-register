package com.funnythingz.register

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.TextView

import com.android.vending.billing.IInAppBillingService
import com.funnythingz.register.adapter.ProductCoinListAdapter
import com.funnythingz.register.model.AppCoin
import com.funnythingz.register.model.AppCoinFactory

import java.util.ArrayList

import com.funnythingz.register.model.BillingResponseResult.*

class MainActivity : AppCompatActivity() {

    private var mProductCoinListAdapter: ProductCoinListAdapter? = null
    private val mHandler = Handler()

    private var mService: IInAppBillingService? = null
    private val mServiceConn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
        }

        override fun onServiceConnected(name: ComponentName,
                                        service: IBinder) {
            mService = IInAppBillingService.Stub.asInterface(service)

            // 商品一覧
            productListView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 課金サービスに接続
        connectIInAppBillingService()

        // 保有コイン
        myAppCoinView()
    }

    private fun connectIInAppBillingService() {
        val serviceIntent = Intent("com.android.vending.billing.InAppBillingService.BIND")
        serviceIntent.setPackage("com.android.vending")
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE)
    }

    // TODO: ユーザーの保有コインを表示
    private fun myAppCoinView() {
        // TODO: APIと通信して保有コインを表示する

        // FIXME: とりあえずダミー
        val myCoinTextView = findViewById(R.id.my_coin_value_text_view) as TextView
        myCoinTextView.text = "0"
    }

    // TODO: 商品情報を取得
    private fun productListView() {
        val requestProductIdList = ArrayList<String>()
        // TODO: Sandboxのアイテムだけ問い合わせる
        requestProductIdList.add("android.test.purchased")
        requestProductIdList.add("android.test.canceled")
        requestProductIdList.add("android.test.refunded")
        requestProductIdList.add("android.test.item_unavailable")

        val query = Bundle()
        query.putStringArrayList("ITEM_ID_LIST", requestProductIdList)

        // リクエストに対するレスポンスを取得する
        val details: Bundle = mService?.getSkuDetails(3, packageName, "inapp", query) ?: return

        // レスポンスコードを取得
        val responseCode = details.getInt("RESPONSE_CODE")

        // 成功した時
        if (OK.equals(responseCode)) {
            // FIXME: 商品リストを取得 (順番はa-z順っぽいので並び替えが必要)
            val responseList = details.getStringArrayList("DETAILS_LIST")
            val appCoinFactory = AppCoinFactory(responseList)

            // レスポンスから商品一覧を取得してViewにはめる
            val productCoinList: ArrayList<AppCoin> = appCoinFactory.createProductCoinList()
            mProductCoinListAdapter = ProductCoinListAdapter(this, mService as IInAppBillingService, R.layout.adapter_product_coin_list_raw, productCoinList)
            val listView = findViewById(R.id.product_coin_list_view) as ListView
            listView.adapter = mProductCoinListAdapter

        }
    }
}
