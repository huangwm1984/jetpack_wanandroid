package com.android.jetpack_mvvm.ui.fragment

import com.android.base.BaseFragment
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.databinding.FragmentSearchBinding
import com.android.jetpack_mvvm.ui.viewmodel.SearchViewModel

class SearchFragment: BaseFragment<SearchViewModel, FragmentSearchBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_search
    }

    override fun initView() {

    }

    override fun initObserver() {

    }

    override fun initData() {

    }
}