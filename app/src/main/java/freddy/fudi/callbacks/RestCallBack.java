package freddy.fudi.callbacks;

/**
 * Created by romelldominguez on 9/19/16.
 */
public interface RestCallBack extends Callback {
    void httpOK(Object... objects);
    void errorHttp(Exception exception,Object... objects);
}
