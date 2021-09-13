package com.android.jetpack_mvvm.bean

/**
 * 搜索热词
 */
data class Search(
    var id: Int = 0,
    var link: String = "",
    var name: String = "",
    var order: Int = 0,
    var visible: Int = 0
)