package com.funnythingz.register.model;

/**
 */
public enum BillingResponseResult {
    // 成功
    OK(0),

    // ダイアログをキャンセルしたか、戻るボタンが押された事によるキャンセル
    USER_CANCELED(1),

    // 課金APIのバージョンがサポート外
    BILLING_UNAVAILABLE(3),

    // リクエストした商品は購入出来ない
    ITEM_UNAVAILABLE(4),

    // 引数が無効、もしくは署名が不正、課金設定がGooglePlayで不備、AndroidManifestに権限の記述無し。
    DEVELOPER_ERROR(5),

    // 処理中に致命的なエラーが発生
    ERROR(6),

    // 既に所有している商品に対しての購入リクエストで失敗
    ITEM_ALREADY_OWNED(7),

    // 所有していない商品に対して消費行動を行おうとして失敗
    ITEM_NOT_OWNED(8);

    private final int id;

    BillingResponseResult(int id) {
        this.id = id;
    }

    public boolean equals(int code) {
        return this.id == code;
    }
}
