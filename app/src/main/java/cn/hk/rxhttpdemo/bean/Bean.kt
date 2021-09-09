package cn.hk.rxhttpdemo.bean

import com.kakayun.lib_frameworkk.net.BaseResponse


data class ApiResponse<T>(val errorCode: Int = 1, val errorMsg: String = "请求错误", val data: T) : BaseResponse<T>() {

    override fun isSuccess() = errorCode == 0

    override fun getResponseCode() = errorCode

    override fun getResponseData() = data

    override fun getResponseMsg() = errorMsg

}
data class UserListData(
    val mobileNumber: String,
    val userId: String,
    val userName: String
)

data class PatientCodeData(val patientCode:String)