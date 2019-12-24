package com.sogou.babel.testfragment

import android.util.Log
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class PrintMethodProxHandler : InvocationHandler {
    val TAG : String = "DynamicHandler:"

    fun log(msg: String) {
        Log.i(TAG, msg)
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        log("${proxy!!.javaClass.simpleName}: ${method!!.name}")
        return method.invoke(proxy, args)
    }

}
