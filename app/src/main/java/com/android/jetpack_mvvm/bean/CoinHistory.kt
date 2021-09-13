package com.android.jetpack_mvvm.bean

/**
 * 获取个人积分获取列表
 */
data class CoinHistory(
    var coinCount: Int = 0,
    var date: Long = 0,
    var desc: String = "",
    var id: Int = 0,
    var reason: String = "",
    var type: Int = 0,
    var userId: Int = 0,
    var userName: String = ""
)