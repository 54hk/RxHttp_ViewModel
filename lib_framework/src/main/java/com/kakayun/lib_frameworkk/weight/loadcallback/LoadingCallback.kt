package com.kakayun.lib_frameworkk.weight.loadcallback

import android.content.Context
import android.view.View
import com.kakayun.lib_frameworkk.R
import com.kingja.loadsir.callback.Callback


class LoadingCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}