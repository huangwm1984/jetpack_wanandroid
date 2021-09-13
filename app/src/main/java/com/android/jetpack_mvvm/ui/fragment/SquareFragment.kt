package com.android.jetpack_mvvm.ui.fragment

import com.android.base.BaseFragment
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.databinding.FragmentSquareBinding
import com.android.jetpack_mvvm.viewmodel.SquareViewModel

class SquareFragment: BaseFragment<SquareViewModel, FragmentSquareBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_square
    }

    override fun initView() {

    }

    override fun initObserver() {

    }

    override fun initData() {

    }
}