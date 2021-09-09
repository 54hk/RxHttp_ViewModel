package cn.hk.rxhttpdemo

import androidx.lifecycle.*
import cn.hk.rxhttpdemo.bean.ApiResponse
import cn.hk.rxhttpdemo.bean.PatientCodeData
import cn.hk.rxhttpdemo.bean.UserListData
import cn.hk.rxhttpdemo.utils.logE
import com.kakayun.lib_frameworkk.base.BaseViewModel
import com.kakayun.lib_frameworkk.ext.request
import com.kakayun.lib_frameworkk.livedata.ResultState
import com.kakayun.lib_frameworkk.net.BaseResponse
import com.kakayun.lib_frameworkk.net.stateCallback.ListDataUiState
import com.kakayun.lib_frameworkk.utils.loge
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.toClass

class HomeFragmentViewModel : BaseViewModel() {

    var radioList = MutableLiveData<ListDataUiState<UserListData>>()

    fun loadData() {
        request({ APIService.userListData() }, {
            radioList.value = ListDataUiState(isSuccess = true, listData = it)
        }, {
            radioList.value = ListDataUiState(isSuccess = false)
        })
    }
}

class MyFragmentViewModel : BaseViewModel() {

    var radioList2 = MutableLiveData<ResultState<PatientCodeData>>()

    fun loadData2() {
        request({ APIService.patientCode() }, radioList2, true)
    }

//            RxHttp.get(APIProtocol.USER_LIST).asResponseList(UserListData::class.java)
////                .life(activity)
//                .subscribe({ data: MutableList<UserListData> ->
//                    logE("main" , "viewModel --- onSuccess")
//                    radioList.value = ListDataUiState(isSuccess = true, listData = data)
//                }, {
//                    logE("main" , "viewModel --- error")
//                    radioList.value = ListDataUiState(isSuccess = false)
//                })
}