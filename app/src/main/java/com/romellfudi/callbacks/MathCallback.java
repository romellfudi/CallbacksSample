package com.romellfudi.callbacks;

/**
 * Created by romelldominguez on 9/19/16.
 */
public class MathCallback {

    Callback callback;

    void Mathsuccessed(Object... objects){
        callback.successed(objects);
    }

    void Mathfailed(NumberFormatException exception,Object... objects){
        callback.failed(exception.getMessage(),objects);
        ;
    }



}
