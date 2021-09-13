package com.android.jetpack_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.base.request
import com.android.jetpack_mvvm.bean.Article
import com.android.jetpack_mvvm.bean.CommonListData
import com.android.jetpack_mvvm.bean.ProjectClassify
import com.android.jetpack_mvvm.http.apiService

class ProjectViewModel: ViewModel() {

    //页码 首页数据页码从0开始
    var pageNo = 0

    var titleData: MutableLiveData<ArrayList<ProjectClassify>> = MutableLiveData()

    var projectData: MutableLiveData<CommonListData<Article>> = MutableLiveData()

    fun getProjectTitleData() {
        request(
            {
                apiService.getProjecTitle()
            },
            {
                titleData.postValue(it)
            },
            {

            }
        )
    }

    fun getProjectData(isRefresh: Boolean, cid: Int) {
        if (isRefresh) {
            pageNo = 0
        }
        request({ apiService.getProjecDataByType(pageNo, cid) }, {
            //请求成功
            pageNo++
            val listData =
                CommonListData(
                    isSuccess = true,
                    isFirst = isRefresh,
                    isEmpty = it.isEmpty(),
                    hasMore = it.hasMore(),
                    isFirstEmpty = isRefresh && it.isEmpty(),
                    datas = it.datas
                )
            projectData.postValue(listData)
        }, {
            //请求失败
            val listData =
                CommonListData(
                    isSuccess = false,
                    errMessage = it.message.toString(),
                    isFirst = isRefresh,
                    datas = arrayListOf<Article>()
                )
            projectData.postValue(listData)
        })
    }


}