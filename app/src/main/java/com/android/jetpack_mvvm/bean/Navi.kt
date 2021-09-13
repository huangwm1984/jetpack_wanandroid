package com.android.jetpack_mvvm.bean

/**
 *  导航数据
 */
data class Navi(
    var articles: List<Article> = listOf(),
    var cid: Int = 0,
    var name: String = ""
)
