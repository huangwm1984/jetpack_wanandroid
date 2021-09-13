package com.android.jetpack_mvvm.bean

/**
 * 分享人对应列表数据
 */
data class Share(
    var coinInfo: CoinInfo = CoinInfo(),
    var shareArticles: ApiPagerResponse<ArrayList<Article>>
)
