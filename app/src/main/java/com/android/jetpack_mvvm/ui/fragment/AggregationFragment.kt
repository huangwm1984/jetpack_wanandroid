package com.android.jetpack_mvvm.ui.fragment

import androidx.fragment.app.Fragment
import com.android.base.BaseFragment
import com.android.base.toHtml
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.databinding.FragmentProjectBinding
import com.android.jetpack_mvvm.ext.init
import com.android.jetpack_mvvm.viewmodel.AggregationViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_project.*

class AggregationFragment: BaseFragment<AggregationViewModel, FragmentProjectBinding>() {

    var titleData = arrayListOf("广场", "每日一问", "体系", "导航")

    private var fragments: ArrayList<Fragment> = arrayListOf()

    override fun layoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {
        //设置tablayout滚动模式
        tab.tabMode = TabLayout.MODE_FIXED
        //初始化viewpager2
        view_pager.init(this, fragments)
    }

    override fun initObserver() {
        initFragment()
        view_pager.adapter?.notifyDataSetChanged()
        view_pager.offscreenPageLimit = fragments.size
        TabLayoutMediator(tab, view_pager) { tab, position -> tab.text = titleData[position].toHtml() }.attach()
    }

    override fun initData() {

    }

    private fun initFragment() {
        fragments.clear()
        fragments.add(SquareFragment())
        fragments.add(AskFragment())
        fragments.add(SystemFragment())
        fragments.add(NavigationFragment())
    }

}