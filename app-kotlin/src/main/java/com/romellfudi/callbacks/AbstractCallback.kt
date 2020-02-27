package com.romellfudi.callbacks

/**
 * Created by romelldominguez on 9/19/16.
 */
abstract class AbstractCallback : Callback {
    abstract override fun successed(vararg objects: Any)
    abstract fun failed(exception: Exception?, vararg objects: Any?)
    override fun failed(vararg objects: Any) {
        failed(null, *objects)
    }
}