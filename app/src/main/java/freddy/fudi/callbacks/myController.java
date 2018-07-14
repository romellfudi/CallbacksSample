package freddy.fudi.callbacks;

import java.net.HttpRetryException;
import java.text.ParseException;

import freddy.fudi.callbacks.interfaces.FailCallback;
import freddy.fudi.callbacks.interfaces.ParseCallback;
import freddy.fudi.callbacks.interfaces.ResponseFailCallback;
import freddy.fudi.callbacks.interfaces.ResponseObjectCallback;
import freddy.fudi.callbacks.interfaces.SuccessCallback;

/**
 * Created by romelldominguez on 9/19/16.
 */
public class myController {

    private static myController instance;
    private static final int codOK = 0;
    private static final int codERROR = -1;
    private Model mModel;

    private myController() {
    }

    public static myController getInstance() {
        if (instance == null)
            instance = new myController();
        return instance;
    }

    public void guardarData(String data, Callback callback) {
        if (!data.isEmpty())
            callback.successed("Se obtuvo la información", data);
        else
            callback.failed("Mensaje de Error");
    }

    public int enviarData(int num, float[] decimals, EnviarCallback enviarCallback) {
        if (num == 0 && decimals.length > 0) {
            enviarCallback.successed(codOK);
            return codOK;
        } else {
            enviarCallback.errorAlParsear(new ParseException("parseo", -1));
            return codERROR;
        }
    }

    public void enviarData(int num, float[] decimals, EnviarCallback enviarCallback, RestCallBack restCallBack) {
        int cod = enviarData(num, decimals, enviarCallback);
        if (cod == codOK) {
            if (enviarCallback == null)
                restCallBack.failed("Enviar data error");
            else {
                restCallBack.httpOK("ok");
            }
        } else {
            enviarCallback.failed("Error al parsear la data de envio");
        }
    }

    public myController enviarData(int num, float[] decimals) {
        instance.mModel = new Model();
        instance.mModel.setNumero(num).setCifras(decimals);
        return instance;
    }

    public void enqueRest(RestCallBack restCallBack) {
        if (mModel != null)
            restCallBack.httpOK(mModel.getNumero(), mModel.getCifras(), mModel);
        else
            restCallBack.errorHttp(new NullPointerException("modelo sin construir"));
    }

    public void resolverProblema(int n, int m, MathCallback mathCallback) {
        mathCallback.Mathsuccessed(codOK);
        mathCallback.Mathfailed(new NumberFormatException("suma incorrecta"), codERROR);
    }

    public void resolverObject(Object object, ObjectCallback objectCallback){
        if (object!=null)
            objectCallback.successed(object);
        else
            objectCallback.failed(new NullPointerException("Referencia Vacia"));
    }

    public void resolverAbstract(int dato, String name, AbstractCallback abstractCallback) {
        if (!name.isEmpty())
            abstractCallback.successed(codOK,
                    new Model().setNumero(dato).setCifras(new float[]{Float.parseFloat(name)}));
        else
            abstractCallback.failed(new NoClassDefFoundError("Clase no encontrada"), codERROR);
    }

    public void getParsedObject(String code, MethodCall methodCall ){
        if (code.isEmpty())
            methodCall.onFail("Codido no existe");
        else {
           try {
               int cod = Integer.parseInt(code);
               methodCall.onSuccess(new Model().setNumero(cod).setCifras(new float[]{cod}));
           }catch (Exception e){
               methodCall.onParse((ParseException)e);
           }
        }
    }

    public void getHttp(String url, HttpCall<Model> httpCall){
        httpCall.errorHTTP("404",new HttpRetryException("",404));
        httpCall.onResponse(new Model().setCifras(null).setNumero(404));
    }

    public void twoSteps(Callback... callbacks){
        for (Callback callback: callbacks){
            if (callback instanceof PreCallback)
                callback.successed("Pre-Success");
            else if (callback instanceof PosCallback)
                callback.failed("Pos-Fail");
        }
    }



    public static abstract class MethodCall implements SuccessCallback, FailCallback, ParseCallback{}

    public static abstract class HttpCall<L> implements ResponseObjectCallback<L>, ResponseFailCallback {}

}
