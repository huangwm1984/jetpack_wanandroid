package com.android.jetpack_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.base.request
import com.android.jetpack_mvvm.bean.*
import com.android.jetpack_mvvm.http.apiService
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class HomeViewModel: ViewModel() {

    //页码 首页数据页码从0开始
    var pageNo = 0

    //首页轮播图数据
    var bannerData: MutableLiveData<ArrayList<Banner>> = MutableLiveData()

    //首页文章列表数据
    var homeData: MutableLiveData<CommonListData<Article>> = MutableLiveData()

    /**
     * 获取广告页数据
     */
    fun getBannerData() {
        request(
            {
                apiService.getBanner()
            },
            {
                bannerData.postValue(it)
            },
            {
                ToastUtils.showShort(it.message)
            })
    }

    /**
     * 获取首页文章列表数据
     * @param isRefresh 是否是刷新，即第一页
     */
    fun getHomeData(isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 0
        }
        request({ getHomeData(pageNo) }, {
            //请求成功
            pageNo++
            var listData = CommonListData(
                isSuccess = true,
                isRefresh = isRefresh,
                isEmpty = it.isEmpty(),
                hasMore = it.hasMore(),
                isFirstEmpty = isRefresh && it.isEmpty(),
                datas = it.datas
            )
            homeData.postValue(listData)
        }, {
            //请求失败
            val listData = CommonListData(
                isSuccess = false,
                isRefresh = isRefresh,
                errMessage = it.message.toString(),
                datas = arrayListOf<Article>()
            )
            homeData.postValue(listData)
            ToastUtils.showShort(it.message)
        })
    }

    /**
     * 获取首页文章+置顶文章数据
     */
    suspend fun getHomeData(pageNo: Int): ApiResponse<ApiPagerResponse<ArrayList<Article>>> {
        //同时异步请求2个接口，请求完成后合并数据
        return withContext(Dispatchers.IO) {
            val listData = async { apiService.getAritrilList(pageNo) }
            //是第一页
            if (pageNo == 0) {
                val topData = async { apiService.getTopAritrilList() }
                listData.await().data.datas.addAll(0, topData.await().data)
                listData.await()
            } else {
                listData.await()
            }
        }
    }

}