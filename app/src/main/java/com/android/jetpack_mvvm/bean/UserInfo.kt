package com.android.jetpack_mvvm.bean

/**
 * 个人信息
 */
data class UserInfo(
    var admin: Boolean = false,
    var chapterTops: List<Any> = listOf(),//List：有序接口，只能读取，不能更改元素；
    var coinCount: Int = 0,
    var collectIds: MutableList<Int> = mutableListOf(), //MutableList：有序接口，可以读写与更改、删除、增加元素。
    var email: String = "",
    var icon: String = "",
    var id: Int = 0,
    var nickname: String = "",
    var password: String = "",
    var publicName: String = "",
    var token: String = "",
    var type: Int = 0,
    var username: String = ""
)