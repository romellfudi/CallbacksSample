package com.romellfudi.callbacks

/**
 * Created by romelldominguez on 9/19/16.
 */
interface EnviarCallback : Callback {
    fun errorAlParsear(exception: Exception?)
}