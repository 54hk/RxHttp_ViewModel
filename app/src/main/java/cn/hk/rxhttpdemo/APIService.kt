package cn.hk.rxhttpdemo

import cn.hk.rxhttpdemo.bean.ApiResponse
import cn.hk.rxhttpdemo.bean.PatientCodeData
import cn.hk.rxhttpdemo.bean.UserListData
import cn.hk.rxhttpdemo.net.Response
import com.kakayun.lib_frameworkk.net.BaseResponse
import kotlinx.coroutines.CoroutineScope
import rxhttp.RxHttp
import rxhttp.async
import rxhttp.toClass
import rxhttp.tryAwait
import rxhttp.wrapper.cahce.CacheMode

class APIService {
    companion object {
        suspend fun userListData() =
            RxHttp.get(APIProtocol.USER_LIST)
                .toClass<ApiResponse<MutableList<UserListData>>>()
                .await()

        suspend fun patientCode() =
            RxHttp.postJson(APIProtocol.PATIENT_CODE)
                .toClass<ApiResponse<PatientCodeData>>()
                .await()
    }
}