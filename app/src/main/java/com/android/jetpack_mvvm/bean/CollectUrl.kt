package com.android.jetpack_mvvm.bean

/**
 *  收藏的网址类
 */
data class CollectUrl(
    var icon: String,
    var id: Int,
    var link: String,
    var name: String,
    var order: Int,
    var userId: Int,
    var visible: Int
)
