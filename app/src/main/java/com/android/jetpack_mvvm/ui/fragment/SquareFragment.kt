package com.android.jetpack_mvvm.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.android.base.BaseFragment
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.databinding.CommonRecyclerviewBinding
import com.android.jetpack_mvvm.ext.*
import com.android.jetpack_mvvm.viewmodel.AggregationViewModel
import com.drakeet.multitype.MultiTypeAdapter
import com.kingja.loadsir.core.LoadService
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import kotlinx.android.synthetic.main.common_recyclerview.*

/**
 * 广场
 */
class SquareFragment: BaseFragment<AggregationViewModel, CommonRecyclerviewBinding>() {

    private val articleAdapter: MultiTypeAdapter by lazy { MultiTypeAdapter() }

    private lateinit var loadsir: LoadService<Any>

    override fun layoutId(): Int {
        return R.layout.fragment_project_child
    }

    override fun initView() {
        //状态页配置
        loadsir = loadServiceInit(swipeRefresh) {
            //点击重试时触发的操作
            loadsir.showLoading()
            viewModel.getSquareData(true)
        }
        //初始化adapter
        articleAdapter.initArticleAdapter()
        //初始化recyclerView
        recyclerView.init(LinearLayoutManager(context), articleAdapter).run {
            this.initFooter(SwipeRecyclerView.LoadMoreListener {
                viewModel.getSquareData(false)
            })
        }
        //初始化 SwipeRefreshLayout
        swipeRefresh.setOnRefreshListener {
            viewModel.getSquareData(true)
        }
    }

    override fun initObserver() {
        viewModel.run {
            squareData.observe(viewLifecycleOwner, {
                loadArticleListData(it, articleAdapter, loadsir, recyclerView, swipeRefresh)
            })
        }
    }

    override fun initData() {
        //设置界面 加载中
        loadsir.showLoading()
        //请求文章列表数据
        viewModel.getSquareData(true)
    }
}