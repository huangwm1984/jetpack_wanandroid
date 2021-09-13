package com.android.jetpack_mvvm.widget.loadsir_callback

import com.android.jetpack_mvvm.R
import com.kingja.loadsir.callback.Callback

class ErrorCallback: Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_error
    }
}