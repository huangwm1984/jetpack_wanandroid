package com.android.jetpack_mvvm.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.base.BaseFragment
import com.android.base.toHtml
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.databinding.FragmentProjectBinding
import com.android.jetpack_mvvm.ext.init
import com.android.jetpack_mvvm.ext.loadServiceInit
import com.android.jetpack_mvvm.ext.showLoading
import com.android.jetpack_mvvm.viewmodel.ProjectViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kingja.loadsir.core.LoadService
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment: BaseFragment<ProjectViewModel, FragmentProjectBinding>() {

    private lateinit var loadsir: LoadService<Any>

    var fragments: ArrayList<Fragment> = arrayListOf()

    override fun layoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {
        //状态页配置
        loadsir = loadServiceInit(view_pager) {
            //点击重试时触发的操作
            loadsir.showLoading()
            //requestProjectViewModel.getProjectTitleData()
        }
        //初始化viewpager2
        view_pager.init(this, fragments)
    }

    override fun initObserver() {
        viewModel.run {
            titleData.observe(viewLifecycleOwner, Observer {
                fragments.clear()
                it.forEach {
                    fragments.add(ProjectChildFragment.newInstance(it.id))
                }
                view_pager.adapter?.notifyDataSetChanged()
                view_pager.offscreenPageLimit = fragments.size
                TabLayoutMediator(tab, view_pager) { tab, position -> tab.text = it[position].name.toHtml() }.attach()
            })
        }
    }

    override fun initData() {
        //设置界面 加载中
        loadsir.showLoading()
        //请求标题数据
        viewModel.getProjectTitleData()
    }
}