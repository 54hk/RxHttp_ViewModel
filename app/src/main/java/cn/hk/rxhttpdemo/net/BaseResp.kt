package cn.hk.rxhttpdemo.net

import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.TypeParser
import rxhttp.wrapper.utils.convertTo
import java.io.IOException
import java.lang.reflect.Type

data class Response<T>(
    var createDate: String,
    // 0、成功
    var flag: Int = 0,
    var data: T,
    var message: String,
    var failed: Boolean,

    var rel: Boolean,
    var status: Int,
    var errmsg: String,
)

@Parser(name = "Response", wrappers = [List::class])
open class ResponseParser<T> : TypeParser<T> {
    //注意，以下两个构造方法是必须的
    protected constructor() : super()
    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T {
        val data: Response<T> = response.convertTo(Response::class, *types)
        val t = data.data //获取data字段
        if (data.flag != 0) {
            //flag == 1,token失效,清空数据，直接往登陆跳
//            SharedPreferencesUtil.removeLoginState()
//            ActivityCollector.finishAll()
//            BaseActivity.activity.startActivity<LoginActivity>(null)
            throw ParseException("${data.flag}", data.message, response)
        }
        return t
    }
}