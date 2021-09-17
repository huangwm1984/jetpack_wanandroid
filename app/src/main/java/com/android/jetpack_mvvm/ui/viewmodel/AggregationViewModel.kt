package com.android.jetpack_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.base.request
import com.android.jetpack_mvvm.bean.Article
import com.android.jetpack_mvvm.bean.CommonListData
import com.android.jetpack_mvvm.http.apiService

class AggregationViewModel: ViewModel(){

    private var pageNo = 0

    //广场数据
    var squareData: MutableLiveData<CommonListData<Article>> = MutableLiveData()

    /**
     * 获取广场数据
     */
    fun getSquareData(isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 0
        }
        request({ apiService.getSquareData(pageNo) }, {
            //请求成功
            pageNo++
            val listData =
                CommonListData(
                    isSuccess = true,
                    isRefresh = isRefresh,
                    isEmpty = it.isEmpty(),
                    hasMore = it.hasMore(),
                    isFirstEmpty = isRefresh && it.isEmpty(),
                    datas = it.datas
                )
            squareData.value = listData
        }, {
            //请求失败
            val listData =
                CommonListData(
                    isSuccess = false,
                    errMessage = it.message.toString(),
                    isRefresh = isRefresh,
                    datas = arrayListOf<Article>()
                )
            squareData.value = listData
        })
    }

}