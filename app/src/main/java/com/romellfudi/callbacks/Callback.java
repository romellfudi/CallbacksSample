package com.romellfudi.callbacks;

/**
 * Created by romelldominguez on 9/19/16.
 */
public interface Callback {
    void successed(Object... objects);

    void failed(Object... objects);
}
