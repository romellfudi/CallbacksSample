package com.romellfudi.callbacks;

/**
 * Created by romelldominguez on 9/30/16.
 */

public class TemplateController<G,H> {
    public void methodStatickCall(TemplateCallback<G,H> callback){
        callback.methodSuperReturnA(null);
        callback.methodPoorReturnY(null);
    }

    public static abstract class TemplateCallback<TC,UC>
            implements IntTemUnder<TC,UC>{

    }
}
