package com.kakayun.lib_frameworkk.ext

import java.lang.reflect.ParameterizedType



@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}






