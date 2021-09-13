package com.android.base

/**
 *  服务器返回数据的基类
 */
abstract class BaseResponse<T> {

    abstract fun isSucces(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String

}