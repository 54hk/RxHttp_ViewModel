package cn.hk.rxhttpdemo

import rxhttp.wrapper.annotation.DefaultDomain

class APIProtocol {
    companion object {
        //正式接口
        @DefaultDomain
        const val BASE_URL: String = "https://dev.aimanrenjian.net:8443/bgpcf/api1/"

        //图形验证
        const val IMG_CODE_URL = "auth/captcha"

        //登陆
        const val LOGIN_URL = "auth/login"

        //验证码
        const val LOGIN_SMS_CODE_URL = "auth/smsCode"

        //入标标准
        const val ENTRY_RULE_URL = "dict/question/pre"

        //新建病例接口
        const val NEW_CASE_URL = "patient/newCase"

        //上传图片
        const val OSS_SIGN_UPLOAD = "oss/signUpload"

        //首页访问一
        const val VISIT_1_URL = "patient/centerPatientQuery"

        //访视一，目录
        const val VISIT1_CATALOG_URL = "patient/stageQuery"

        //访视一的解析问题
        const val PHASE_QUESTION_URL_1 = "patient/phaseQuestionData"

        //访视一：保存解析问题
        const val SAVE_VISIT_PHASH_QUESTION_1 = "patient/saveAnswerData"

        //不良事件地址
        const val ADVERSE_EVENT_ADDRESS = "patient/saveAdverseData"

        //唤醒检查token
        const val TOKEN_STATUS = "dict/tokenStatus"

        //性别列表
        const val SEX_LIST = "dict/sexList"

        //录入完成
        const val INPUT_SUCCESS = "patient/visit1/ending/"

        //录入完成
        const val INPUT_SUCCESS_2 = "patient/visit2/ending/"


        //首页访问二
        const val VISIT_2_URL = "patient/center/visit2"

        //访问二 目录
        const val VISIT2_CATALOG_URL = "patient/visit2/patient/"

        //获取版本号
        const val GET_VERSION = "auth/version"

        //访视一，质疑列表
        const val VISIT_1_QUESTION = "app/my/question/visit1"

        //访视二，质疑列表
        const val VISIT_2_QUESTION = "app/my/question/visit2"


        //质疑列表详情
        const val VISIT_1_DETAIL = "app/my/question/details"

        //质疑回复
        const val REPLY_QUESTION = "app/my/question/reply"

        //病例id
        const val PATIENT_CODE = "patient/code"

        //未读消息数
        const val UN_READ_COUNT = "message/unreadCount"

        //首页未读数
        const val UN_READ_COUNT2 = "app/my/notify/data"

        //未读消息列表
        const val UNREAD_MESSAGE_LIST = "message/myMessage"

        //读
        const val READ_URL = "message/read"

        //获取人数
        const val USER_LIST = "app/my/center/userList"

        //搜索数据,访视一
        const val SEARCH_DATA_1 = "app/my/visit/data/1"

        //搜索数据,访视二
        const val SEARCH_DATA_2 = "app/my/visit/data/2"
    }
}