package com.android.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VM: ViewModel, VB: ViewDataBinding> : AppCompatActivity() {

    lateinit var viewModel: VM

    lateinit var dataBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initViewModel()
        initView()
        initObserver()
    }

    private fun initDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, layoutId())
        dataBinding.lifecycleOwner = this
    }

    private fun initViewModel() {
       viewModel =  ViewModelProvider(this).get(getVmClazz(this))
    }

    abstract fun layoutId(): Int

    abstract fun initView()

    abstract fun initObserver()

}