package com.romellfudi.callbacks.interfaces

/**
 * Created by romelldominguez on 9/19/16.
 */
interface ResponseObjectCallback<T> {
    fun onResponse(template: T, vararg objects: Any)
}