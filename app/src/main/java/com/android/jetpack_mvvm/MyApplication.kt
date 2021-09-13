package com.android.jetpack_mvvm

import android.app.Application
import androidx.multidex.MultiDex
import com.android.jetpack_mvvm.widget.loadsir_callback.EmptyCallback
import com.android.jetpack_mvvm.widget.loadsir_callback.ErrorCallback
import com.android.jetpack_mvvm.widget.loadsir_callback.LoadingCallback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.tencent.mmkv.MMKV

class MyApplication: Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
        MMKV.initialize(this.filesDir.absolutePath + "/mmkv")
        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())
            .addCallback(ErrorCallback())
            .addCallback(EmptyCallback())
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()
    }



}