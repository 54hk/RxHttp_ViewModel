package com.kakayun.lib_frameworkk.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kakayun.lib_frameworkk.R
import com.kakayun.lib_frameworkk.ext.dismissLoadingExt
import com.kakayun.lib_frameworkk.ext.getVmClazz
import com.kakayun.lib_frameworkk.ext.showLoadingExt
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX

/**
 * Created by YuHang
 * c
 * 2020/12/14 3:49 PM
 */
abstract class BaseActivity<VM: BaseViewModel, BD: ViewDataBinding>: AppCompatActivity(){
    lateinit var mViewBind: BD
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UltimateBarX.with(this)
            .transparent()
            .light(true)
            .lvLightColorRes(R.color.black)
            .applyStatusBar();
        mViewBind = DataBindingUtil.setContentView(this, getLayoutId())
        mViewBind.lifecycleOwner = this
        mViewModel = createViewModel()
        registerUiChange()
        initView()
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    abstract fun getLayoutId(): Int

    open fun initView(){


    }

    open fun showLoading(message: String = "请求网络中..."){
        showLoadingExt(message)
    }

    open fun dismissLoading(){
        dismissLoadingExt()
    }

    private fun registerUiChange() {
        mViewModel.loadingChange.showDialog.observeInActivity(this, Observer {
            showLoading(it)
        })

        mViewModel.loadingChange.dismissDialog.observeInActivity(this, Observer {
            dismissLoading()
        })
    }

}