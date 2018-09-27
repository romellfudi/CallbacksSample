package com.romellfudi.callbacks.interfaces;

import java.text.ParseException;

/**
 * Created by romelldominguez on 9/19/16.
 */
public interface ParseCallback {
    void onParse(ParseException exception, Object... objects);
}
