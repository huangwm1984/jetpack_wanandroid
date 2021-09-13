package com.android.jetpack_mvvm.ui.fragment

import com.android.base.BaseFragment
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.databinding.FragmentPublicBinding
import com.android.jetpack_mvvm.viewmodel.PublicViewModel

class PublicFragment: BaseFragment<PublicViewModel, FragmentPublicBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_public
    }

    override fun initView() {

    }

    override fun initObserver() {

    }

    override fun initData() {

    }
}