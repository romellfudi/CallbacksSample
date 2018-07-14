package freddy.fudi.callbacks;

/**
 * Created by romelldominguez on 9/19/16.
 */
public interface OtherCallback {

    void crashed(Exception exception, String message);
    void pass(Model model);
}
