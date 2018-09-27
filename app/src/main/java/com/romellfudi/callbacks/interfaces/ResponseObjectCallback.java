package com.romellfudi.callbacks.interfaces;

/**
 * Created by romelldominguez on 9/19/16.
 */
public interface ResponseObjectCallback<T> {
    void onResponse(T template,Object... objects);
}
