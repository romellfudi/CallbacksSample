package com.romellfudi.callbacks

/**
 * Created by romelldominguez on 9/19/16.
 */
interface Callback {
    fun successed(vararg objects: Any)
    fun failed(vararg objects: Any)
}