package freddy.fudi.callbacks.interfaces;

import freddy.fudi.callbacks.Model;

/**
 * Created by romelldominguez on 9/19/16.
 */
public interface ResponseObjectCallback<T> {
    void onResponse(T template,Object... objects);
}
