package com.kakayun.lib_frameworkk.base

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kakayun.lib_frameworkk.ext.dismissLoadingExt
import com.kakayun.lib_frameworkk.ext.getVmClazz
import com.kakayun.lib_frameworkk.ext.hideSoftKeyboard
import com.kakayun.lib_frameworkk.ext.showLoadingExt
import com.kakayun.lib_frameworkk.net.manager.NetState
import com.kakayun.lib_frameworkk.net.manager.NetworkStateManager

/**
 * Created by YuHang
 * c
 * 2020/12/11 11:20 AM
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {
    //是否第一次加载
    private var isFirst: Boolean = true
    private val handler = Handler()

    lateinit var mViewBind: DB
    lateinit var mViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBind = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = createViewModel()
        registerUiChange()
        initView(mViewBind.root)
        initData()
        viewListener()
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    private fun registerUiChange() {
        mViewModel.loadingChange.showDialog.observeInFragment(this, Observer {
            showLoading(it)
        })

        mViewModel.loadingChange.dismissDialog.observeInFragment(this, Observer {
            dismissLoading()
        })
    }

    open fun showLoading(message: String = "请求网络中...") {
        showLoadingExt(message)
    }

    open fun dismissLoading() {
        dismissLoadingExt()
    }

    abstract fun getLayoutId(): Int

    open fun initView(rootView: View) {

    }

    open fun initData() {

    }

    open fun viewListener() {

    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            // 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿
            handler.postDelayed({
                lazyLoadData()
                //在Fragment中，只有懒加载过了才能开启网络变化监听
                NetworkStateManager.instance.mNetworkStateCallback.observeInFragment(
                    this,
                    Observer {
                        //不是首次订阅时调用方法，防止数据第一次监听错误
                        if (!isFirst) {
                            onNetworkStateChanged(it)
                        }
                    })
                isFirst = false
            }, lazyLoadTime())
        }
    }

    /**
     * 将非该Fragment绑定的ViewModel添加 loading回调 防止出现请求时不显示 loading 弹窗bug
     * @param viewModels Array<out BaseViewModel>
     */
    protected fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observeInFragment(this, Observer {
                showLoading(it)
            })
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observeInFragment(this, Observer {
                dismissLoading()
            })
        }
    }

    /**
     * 懒加载
     */
    open fun lazyLoadData() {}

    /**
     * 网络变化监听
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿  bug
     * 这里传入你想要延迟的时间，延迟时间可以设置比转场动画时间长一点 单位： 毫秒
     * 不传默认 300毫秒
     * @return Long
     */
    open fun lazyLoadTime(): Long {
        return 300
    }

    override fun onPause() {
        super.onPause()
        hideSoftKeyboard(activity)
    }

}