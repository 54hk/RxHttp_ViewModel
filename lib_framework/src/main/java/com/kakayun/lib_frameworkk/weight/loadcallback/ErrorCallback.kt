package com.kakayun.lib_frameworkk.weight.loadcallback

import com.kakayun.lib_frameworkk.R
import com.kingja.loadsir.callback.Callback


class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}