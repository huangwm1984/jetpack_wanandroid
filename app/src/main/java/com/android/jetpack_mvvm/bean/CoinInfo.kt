package com.android.jetpack_mvvm.bean

/**
 *  积分排行榜接口
 */
data class CoinInfo(
    var coinCount: Int = 0,
    var level: Int = 0,
    var nickname: String = "",
    var rank: String = "",
    var userId: Int = 0,
    var username: String = ""
)
