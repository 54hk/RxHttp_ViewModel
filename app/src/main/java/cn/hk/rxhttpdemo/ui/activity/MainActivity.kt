package cn.hk.rxhttpdemo.ui.activity

import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import cn.hk.rxhttpdemo.R
import cn.hk.rxhttpdemo.databinding.ActivityMainBinding
import com.kakayun.lib_frameworkk.base.BaseActivity
import com.kakayun.lib_frameworkk.base.BaseViewModel

/**
 * RxHttp
 * https://www.jianshu.com/p/2557ef0e48a0
 * https://juejin.cn/post/6844904100090347528#heading-2
 * https://juejin.cn/post/6844904016380428302
 */
class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {

    private var navController: NavController? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        //获取navController
        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).findNavController()
//        通过setupWithNavController将底部导航和导航控制器进行绑定
        NavigationUI.setupWithNavController(mViewBind.navigationView, navController!!)

    }
}