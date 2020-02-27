package com.romellfudi.callbacks

/**
 * Created by romelldominguez on 9/19/16.
 */
interface OtherCallback {
    fun crashed(exception: Exception?, message: String?)
    fun pass(model: Model?)
}