package com.romellfudi.callbacks.interfaces

/**
 * Created by romelldominguez on 9/19/16.
 */
interface ResponseFailCallback {
    fun errorHTTP(codeError: String, exception: Exception, vararg objects: Any)
}