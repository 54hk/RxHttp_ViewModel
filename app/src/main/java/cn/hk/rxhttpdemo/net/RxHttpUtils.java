package cn.hk.rxhttpdemo.net;


import static cn.hk.rxhttpdemo.utils.ExtendKt.generateNumber;

import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.hk.rxhttpdemo.Globals;
import cn.hk.rxhttpdemo.MyApplication;
import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.cahce.CacheMode;
import rxhttp.wrapper.ssl.HttpsUtils;

public class RxHttpUtils {
    /**
     * 初始化网络请求
     *
     * @param application
     */
    public static void init(MyApplication application) {
        //设置缓存策略，非必须
        File cacheFile = new File(application.getExternalCacheDir(), Globals.NET_CACHE_NAME);
        // 网络请求框架初始化
        RxHttpPlugins.init(
                getDefaultOkHttpClient())      //自定义OkHttpClient对象
                .setDebug(Globals.HAVE_LOG)                //是否开启调试模式，开启后，logcat过滤RxHttp，即可看到整个请求流程日志
                .setCache(cacheFile, 100 * 1024 * 1024, CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE)  //配置缓存目录，最大size及缓存模式
                .setOnParamAssembly(param -> param.addHeader("bgpcfTokenId", "TOKEN_2E05015D948D09408098F1E904E7DED77D6D62057693")
                        .addHeader("requestTransactionId", "APP" + generateNumber()));
//                .setExcludeCacheKeys(String...)   //设置一些key，不参与cacheKey的组拼
//                .setResultDecoder(Function)       //设置数据解密/解码器，非必须
//                .setConverter(IConverter)         //设置全局的转换器，非必须
//                .setOnParamAssembly(Function);    //设置公共参数/请求头回调
    }

    private static OkHttpClient getDefaultOkHttpClient() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }


}
