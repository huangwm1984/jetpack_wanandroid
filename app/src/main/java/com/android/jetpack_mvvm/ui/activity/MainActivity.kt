package com.android.jetpack_mvvm.ui.activity

import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.android.base.BaseActivity
import com.android.jetpack_mvvm.R
import com.android.jetpack_mvvm.databinding.ActivityMainBinding
import com.android.jetpack_mvvm.viewmodel.MainViewModel
import com.blankj.utilcode.util.ToastUtils

class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>() {
    var exitTime = 0L

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){//OnBackPressedCallback事件是一个匿名类的对象，用object来修饰
            override fun handleOnBackPressed() {
                val nav = Navigation.findNavController(this@MainActivity, R.id.host_fragment)
                if (nav.currentDestination != null && nav.currentDestination!!.id != R.id.mainfragment) {
                    //如果当前界面不是主页，那么直接调用返回即可
                    nav.navigateUp()
                } else {
                    //是主页
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        ToastUtils.showShort("再按一次退出程序")
                        exitTime = System.currentTimeMillis()
                    } else {
                        finish()
                    }
                }
            }
        })
    }

    override fun initObserver() {

    }
}