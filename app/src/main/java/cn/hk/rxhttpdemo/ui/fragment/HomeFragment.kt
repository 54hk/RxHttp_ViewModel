package cn.hk.rxhttpdemo.ui.fragment

import android.view.View
import cn.hk.rxhttpdemo.HomeFragmentViewModel
import cn.hk.rxhttpdemo.R
import cn.hk.rxhttpdemo.databinding.FragmentHomeBinding
import com.kakayun.lib_frameworkk.base.BaseFragment
import com.kakayun.lib_frameworkk.ext.loadServiceInit
import com.kakayun.lib_frameworkk.ext.showError
import com.kakayun.lib_frameworkk.ext.showLoading
import com.kakayun.lib_frameworkk.net.ExceptionHandle.handleException
import com.kingja.loadsir.core.LoadService
import org.xml.sax.ErrorHandler

class HomeFragment : BaseFragment<HomeFragmentViewModel, FragmentHomeBinding>() {


    override fun getLayoutId(): Int = R.layout.fragment_home


    //界面状态管理者
    private lateinit var loadSir: LoadService<Any>

    override fun initView(rootView: View) {
        super.initView(rootView)
        //状态页配置
        loadSir = loadServiceInit(mViewBind.textView) {
            //点击重试时触发的操作
            loadSir.showLoading()
            mViewModel.loadData()
        }

    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        loadSir.showLoading()
        mViewModel.radioList.observe(this, {
            if (it.isSuccess) {
                loadSir.showSuccess()
                activity?.runOnUiThread {
                    mViewBind.textView.text = "请求成功"
                }
            } else {
                loadSir.showError()
            }
        })
        mViewModel.loadData()
    }

}