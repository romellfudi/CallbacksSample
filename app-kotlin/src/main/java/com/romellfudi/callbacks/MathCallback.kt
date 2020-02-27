package com.romellfudi.callbacks

/**
 * Created by romelldominguez on 9/19/16.
 */
open class MathCallback {
    var callback: Callback? = null
    open fun Mathsuccessed(vararg objects: Any) {
        callback!!.successed(*objects)
    }

    open fun Mathfailed(exception: NumberFormatException, vararg objects: Any?) {
        callback!!.failed(exception.message!!, objects)
    }
}