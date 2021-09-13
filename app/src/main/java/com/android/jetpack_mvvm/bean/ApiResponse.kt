package com.android.jetpack_mvvm.bean

import com.android.base.BaseResponse

/**
 * 根据服务器返回数据的格式进行包装的基类
 */
data class ApiResponse<T>(val errorCode: Int, val errorMsg: String, val data: T) :
    BaseResponse<T>() {

    override fun isSucces(): Boolean {
        return errorCode == 0
    }

    override fun getResponseData(): T {
        return data
    }

    override fun getResponseCode(): Int {
        return errorCode
    }

    override fun getResponseMsg(): String {
        return errorMsg
    }
}