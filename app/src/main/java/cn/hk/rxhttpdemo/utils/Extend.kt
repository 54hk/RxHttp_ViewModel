package cn.hk.rxhttpdemo.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.telephony.TelephonyManager
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hk.rxhttpdemo.Globals
import cn.hk.rxhttpdemo.utils.AppUtils.Companion.isFastClick
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kakayun.lib_frameworkk.ext.showEmpty
import com.kakayun.lib_frameworkk.ext.showError
import com.kakayun.lib_frameworkk.net.stateCallback.ListDataUiState
import com.kingja.loadsir.core.LoadService
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File
import java.util.*


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun logE(tag: String, mgs: String) {
    if (Globals.HAVE_LOG)
        Log.e(tag, "logE -> $mgs")
}


/**
 * 按钮点击,判断了一下快速点击、网络是否可用
 */

fun View.click(block: () -> Unit) {
    setOnClickListener {
        //防止快速点击
//        if (!isFastClick()) {
//            return@setOnClickListener
//        }
//        if (!AppUtils.isNetwork()) {
//            ToastUtil.showShortToast(AppUtils.getString(R.string.string_network_err))
//            return@setOnClickListener
//        }
        block()
    }
}


//使用内联函数的泛型参数 reified 特性来实现
/**
 * 普通activity跳转
 *
 * @param bundle 参数
 */
inline fun <reified T : AppCompatActivity> Context.startActivity(bundle: Bundle?) {
    val intent = Intent(this, T::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    if (bundle != null) {
        intent.putExtra(Globals.BUNDLE, bundle)
    }
    startActivity(intent)
}

/**
 * 获取bundle
 */
fun getBundle(intent: Intent): Bundle {
    return intent.getBundleExtra(Globals.BUNDLE)!!
}


//随机生成8位数字
fun generateNumber(): String {
    val random = Random()
    val sb = StringBuffer()
    for (index in 0 until 6) {
        sb.append(random.nextInt(10))
    }
    return sb.toString()
}

/**
 * 文字，部分加粗
 * @param text 文本
 * @param start 开始加粗处的索引
 * @param end 结束加粗处的索引
 */
fun textPartBold(text: String, start: Int, end: Int): SpannableString {
    return SpannableString(text).apply {
        setSpan(
            StyleSpan(Typeface.BOLD), start, end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 上传图片，返回MultipartBody
 * @param filePath String
 * @param fileName String 必须是.jepg .png .jpg等结尾的
 * @return MultipartBody.Part
 */
fun multipartBody(filePath: String, fileName: String): MultipartBody.Part {
    val requestBody: RequestBody = RequestBody.create(
        "image/jpeg".toMediaTypeOrNull(),
        File(filePath)
    )
    return MultipartBody.Part.createFormData(
        "signFile",
        fileName,
        requestBody
    )
}


/**
 * POST请求体，将map转换成RequestBody
 * @param map 参数
 */
fun requestBody(map: Map<String, String>): RequestBody {

    var jsonObject = JSONObject()
    if (map.isNotEmpty())
        map.forEach {
            jsonObject.put(it.key, it.value)
        }
    return RequestBody.create("application/json".toMediaTypeOrNull(), jsonObject.toString())
}

/**
 * POST请求体，String
 */
fun requestString(param: String): RequestBody {
    return RequestBody.create(
        "application/json".toMediaTypeOrNull(),
        param
    )
}

