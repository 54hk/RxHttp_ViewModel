package cn.hk.rxhttpdemo

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import cn.hk.rxhttpdemo.net.RxHttpUtils
import com.kakayun.lib_frameworkk.weight.loadcallback.EmptyCallback
import com.kakayun.lib_frameworkk.weight.loadcallback.ErrorCallback
import com.kakayun.lib_frameworkk.weight.loadcallback.LoadingCallback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir

class MyApplication : MultiDexApplication() {

    companion object{
        lateinit var mContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        mContext = this
        //rxHttp网络请求初始化
        RxHttpUtils.init(this)

        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())//加载
            .addCallback(ErrorCallback())//错误
            .addCallback(EmptyCallback())//空
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}