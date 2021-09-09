package cn.hk.rxhttpdemo.ui.fragment

import cn.hk.rxhttpdemo.MyFragmentViewModel
import cn.hk.rxhttpdemo.R
import cn.hk.rxhttpdemo.databinding.FragmentMyBinding
import com.kakayun.lib_frameworkk.base.BaseFragment
import com.kakayun.lib_frameworkk.ext.parseState

class MyFragment : BaseFragment<MyFragmentViewModel, FragmentMyBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_my

    override fun lazyLoadData() {
        mViewModel.radioList2.observe(viewLifecycleOwner, {
            parseState(it, {
                mViewBind.textView.text = "加载成功"
            }, {
                mViewBind.textView.text = "请求失败"
            })
        })
        mViewModel.loadData2()
    }


}