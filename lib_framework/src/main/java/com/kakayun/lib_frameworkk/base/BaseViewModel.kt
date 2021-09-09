package com.kakayun.lib_frameworkk.base

import androidx.lifecycle.ViewModel
import com.kakayun.lib_frameworkk.livedata.EventLiveData

/**
 * Created by YuHang
 * c
 * 4/29/21 3:08 PM
 */
open class BaseViewModel: ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }


    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { EventLiveData<String>() }
        //隐藏
        val dismissDialog by lazy { EventLiveData<Boolean>() }
    }
}