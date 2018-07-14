package freddy.fudi.callbacks;

/**
 * Created by romelldominguez on 9/19/16.
 */
public abstract class AbstractCallback implements Callback {

    public abstract void successed(Object... objects);
    public abstract void failed(Exception exception, Object... objects);

    @Override
    public void failed(Object... objects) {
        failed(null, objects);
    }
}
