package com.android.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VM: ViewModel, VB: ViewDataBinding> : Fragment() {

    lateinit var viewModel: VM

    lateinit var databinding: VB

    lateinit var rootActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        rootActivity = context as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        databinding.lifecycleOwner = this
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
        initObserver()
        initData()
    }

    private fun initViewModel() {
       viewModel =  ViewModelProvider(this).get(getVmClazz(this))
    }

    abstract fun layoutId(): Int

    abstract fun initView()

    abstract fun initObserver()

    abstract fun initData()


}