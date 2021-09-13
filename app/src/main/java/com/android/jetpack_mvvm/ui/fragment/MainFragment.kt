package com.android.jetpack_mvvm.ui.fragment

import com.android.base.BaseFragment
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.databinding.FragmentMainBinding
import com.android.jetpack_mvvm.ext.init
import com.android.jetpack_mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: BaseFragment<MainViewModel, FragmentMainBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        viewPager2.init(this)//初化viewpager2
        bottomNavigationView.init(bottomNavigationView, viewPager2)//初化bottomNavigationView
    }

    override fun initObserver() {

    }

    override fun initData() {

    }
}