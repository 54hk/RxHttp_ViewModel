package cn.hk.rxhttpdemo.utils


class AppUtils {
    companion object {

        // 防止快速点击：两次点击按钮之间的点击间隔不能少于400毫秒
        private const val MIN_CLICK_DELAY_TIME = 400
        private var lastClickTime: Long = 0

        @JvmStatic
        fun isFastClick(): Boolean {
            var flag = false
            val curClickTime = System.currentTimeMillis()
            if (curClickTime - lastClickTime >= MIN_CLICK_DELAY_TIME) {
                flag = true
            }
            lastClickTime = curClickTime

            return flag
        }
    }

}