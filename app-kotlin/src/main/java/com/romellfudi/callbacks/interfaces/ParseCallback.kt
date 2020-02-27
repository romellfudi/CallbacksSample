package com.romellfudi.callbacks.interfaces

import java.text.ParseException

/**
 * Created by romelldominguez on 9/19/16.
 */
interface ParseCallback {
    fun onParse(exception: ParseException, vararg objects: Any?)
}