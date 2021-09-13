package com.android.jetpack_mvvm.ui.fragment

import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.base.BaseFragment
import com.android.base.nav
import com.android.base.navigateAction
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.bean.Banner
import com.android.jetpack_mvvm.databinding.FragmentHomeBinding
import com.android.jetpack_mvvm.ext.*
import com.android.jetpack_mvvm.viewmodel.HomeViewModel
import com.drakeet.multitype.MultiTypeAdapter
import com.kingja.loadsir.core.LoadService
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.zhpan.bannerview.BannerViewPager
import kotlinx.android.synthetic.main.common_recyclerview.*
import kotlinx.android.synthetic.main.common_toolbar.*
import me.hgj.jetpackmvvm.demo.app.weight.banner.HomeBannerAdapter
import me.hgj.jetpackmvvm.demo.app.weight.banner.HomeBannerViewHolder

class HomeFragment: BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private lateinit var loadsir: LoadService<Any>

    private val articleAdapter: MultiTypeAdapter by lazy { MultiTypeAdapter() }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        //状态页配置
        loadsir = loadServiceInit(swipeRefresh) {
            //点击重试时触发的操作
            loadsir.showLoading()
            viewModel.getBannerData()
            viewModel.getHomeData(true)
        }
        //初始化 toolbar
        toolbar.let {
            it.inflateMenu(R.menu.menu_home)
            it.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.home_search -> {
                        nav().navigateAction(R.id.action_mainfragment_to_searchFragment)
                    }
                }
                true
            }
        }
        //初始化adapter
        articleAdapter.initArticleAdapter()
        //初始化recyclerView
        recyclerView.init(LinearLayoutManager(context), articleAdapter).run {
            this.initFooter(SwipeRecyclerView.LoadMoreListener {
                viewModel.getHomeData(false)
            })
        }
        //初始化 SwipeRefreshLayout
        swipeRefresh.setOnRefreshListener {
            viewModel.getHomeData(true)
        }
    }

    override fun initObserver() {
        viewModel.run {
            //监听轮播图请求的数据变化
            bannerData.observe(viewLifecycleOwner, Observer {
                //请求轮播图数据成功，添加轮播图到headview ，如果等于0说明没有添加过头部，添加一个
                if (recyclerView.headerCount == 0) {
                    val headView = LayoutInflater.from(context).inflate(R.layout.layout_banner, null).apply {
                        findViewById<BannerViewPager<Banner, HomeBannerViewHolder>>(R.id.banner_view).run {
                            adapter = HomeBannerAdapter()
                            setLifecycleRegistry(lifecycle)
                            setOnPageClickListener {
                                //nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {putParcelable("bannerdata", data[it])})
                            }
                            create(it)
                        }
                    }
                    recyclerView.addHeaderView(headView)
                    recyclerView.scrollToPosition(0)
                }
            })
            //监听首页文章列表请求的数据变化
            homeData.observe(viewLifecycleOwner, Observer {
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
        //设置界面 加载中
        loadsir.showLoading()
        //请求轮播图数据
        viewModel.getBannerData()
        //请求文章列表数据
        viewModel.getHomeData(true)
    }
}