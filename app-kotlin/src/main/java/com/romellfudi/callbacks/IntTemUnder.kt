package com.romellfudi.callbacks

/**
 * Created by romelldominguez on 9/30/16.
 */
interface IntTemUnder<X, Y> : IntTemSuper<X, Y> {
    fun methodPoorReturnY(x: X): Y
}