package com.android.jetpack_mvvm.ui.fragment

import com.android.base.BaseFragment
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.databinding.FragmentMeBinding
import com.android.jetpack_mvvm.viewmodel.MeViewModel

class MeFragment: BaseFragment<MeViewModel, FragmentMeBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initView() {

    }

    override fun initObserver() {

    }

    override fun initData() {

    }
}