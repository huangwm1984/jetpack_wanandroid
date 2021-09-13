package com.android.jetpack_mvvm.bean

import java.io.Serializable

/**
 *  分页数据的基类
 */
data class ApiPagerResponse<T>(
    var datas: T,
    var curPage: Int = 0,
    var offset: Int = 0,
    var over: Boolean = false,
    var pageCount: Int = 0,
    var size: Int = 0,
    var total: Int = 0,
) : Serializable {
    /**
     * 数据是否为空
     */
    fun isEmpty() = (datas as List<*>).size == 0

    /**
     * 是否为刷新
     */
    fun isRefresh() = offset == 0

    /**
     * 是否还有更多数据
     */
    fun hasMore() = !over
}