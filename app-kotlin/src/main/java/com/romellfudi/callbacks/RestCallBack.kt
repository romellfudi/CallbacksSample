package com.romellfudi.callbacks

/**
 * Created by romelldominguez on 9/19/16.
 */
interface RestCallBack : Callback {
    fun httpOK(vararg objects: Any?)
    fun errorHttp(exception: Exception?, vararg objects: Any?)
}