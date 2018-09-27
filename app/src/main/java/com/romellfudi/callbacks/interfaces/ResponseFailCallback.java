package com.romellfudi.callbacks.interfaces;

/**
 * Created by romelldominguez on 9/19/16.
 */
public interface ResponseFailCallback {
    void errorHTTP(String codeError, Exception exception, Object... objects);
}
