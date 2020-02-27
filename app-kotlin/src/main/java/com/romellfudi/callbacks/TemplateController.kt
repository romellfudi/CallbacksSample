package com.romellfudi.callbacks

/**
 * Created by romelldominguez on 9/30/16.
 */
class TemplateController<G, H> {
    fun methodStatickCall(callback: TemplateCallback<G?, H?>) {
        callback.methodSuperReturnA(null)
        callback.methodPoorReturnY(null)
    }

    abstract class TemplateCallback<TC, UC> : IntTemUnder<TC, UC>
}