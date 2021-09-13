package com.android.jetpack_mvvm.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.base.BaseFragment
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.databinding.FragmentProjectChildBinding
import com.android.jetpack_mvvm.ext.*
import com.android.jetpack_mvvm.viewmodel.ProjectViewModel
import com.drakeet.multitype.MultiTypeAdapter
import com.kingja.loadsir.core.LoadService
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import kotlinx.android.synthetic.main.common_recyclerview.*

class ProjectChildFragment : BaseFragment<ProjectViewModel, FragmentProjectChildBinding>() {

    private var cid = 0

    private lateinit var loadsir: LoadService<Any>

    private val articleAdapter: MultiTypeAdapter by lazy { MultiTypeAdapter() }


    companion object {
        fun newInstance(cid: Int): ProjectChildFragment {
            val args = Bundle()
            args.putInt("cid", cid)
            val fragment = ProjectChildFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_project_child
    }

    override fun initView() {
        arguments?.run {
            cid = getInt("cid")
        }
        //状态页配置
        loadsir = loadServiceInit(swipeRefresh) {
            //点击重试时触发的操作
            loadsir.showLoading()
            viewModel.getProjectData(true, cid)
        }
        //初始化adapter
        articleAdapter.initArticleAdapter()
        //初始化recyclerView
        recyclerView.init(LinearLayoutManager(context), articleAdapter).run {
            this.initFooter(SwipeRecyclerView.LoadMoreListener {
                viewModel.getProjectData(false, cid)
            })
        }
        //初始化 SwipeRefreshLayout
        swipeRefresh.setOnRefreshListener {
            viewModel.getProjectData(true, cid)
        }
    }

    override fun initObserver() {
        viewModel.run {
            projectData.observe(viewLifecycleOwner, Observer {
                swipeRefresh.isRefreshing = false
                recyclerView.loadMoreFinish(it.isEmpty, it.hasMore)
                if (it.isSuccess) {
                    //成功
                    when {
                        //第一页并没有数据 显示空布局界面
                        it.isFirstEmpty -> {
                            loadsir.showEmpty()
                        }
                        //是第一页
                        it.isFirst -> {
                            articleAdapter.items = it.datas
                            loadsir.showSuccess()
                        }
                        //不是第一页
                        else -> {
                            articleAdapter.addData(it.datas)
                            loadsir.showSuccess()
                        }
                    }
                } else {
                    //失败
                    if (it.isFirst) {
                        //如果是第一页，则显示错误界面，并提示错误信息
                        loadsir.showError(it.errMessage)
                    } else {
                        recyclerView.loadMoreError(0, it.errMessage)
                    }
                }
            })
        }
    }

    override fun initData() {
        loadsir.showLoading()
        viewModel.getProjectData(true, cid)
    }
}