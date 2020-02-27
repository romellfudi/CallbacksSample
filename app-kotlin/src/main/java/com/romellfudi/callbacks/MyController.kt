package com.romellfudi.callbacks

import com.romellfudi.callbacks.interfaces.*
import java.net.HttpRetryException
import java.text.ParseException

/**
 * Created by romelldominguez on 9/19/16.
 */
class MyController private constructor() {
    private var mModel: Model? = null
    fun guardarData(data: String, callback: Callback) {
        if (!data.isEmpty()) callback.successed("Se obtuvo la información", data) else callback.failed("Mensaje de Error")
    }

    fun enviarData(num: Int, decimals: FloatArray, enviarCallback: EnviarCallback?): Int {
        return if (num == 0 && decimals.isNotEmpty()) {
            enviarCallback!!.successed(codOK)
            codOK
        } else {
            enviarCallback!!.errorAlParsear(ParseException("parseo", -1))
            codERROR
        }
    }

    fun enviarData(num: Int, decimals: FloatArray, enviarCallback: EnviarCallback?, restCallBack: RestCallBack) {
        val cod = enviarData(num, decimals, enviarCallback)
        if (cod == codOK) {
            if (enviarCallback == null) restCallBack.failed("Enviar data error") else {
                restCallBack.httpOK("ok")
            }
        } else {
            enviarCallback!!.failed("Error al parsear la data de envio")
        }
    }

    fun enviarData(num: Int, decimals: FloatArray?): MyController? {
        instance!!.mModel = Model().setNumero(num).setCifras(decimals)
        return instance
    }

    fun enqueRest(restCallBack: RestCallBack) {
        if (mModel != null) restCallBack.httpOK(mModel!!.numero, mModel!!.cifras, mModel!!) else restCallBack.errorHttp(NullPointerException("modelo sin construir"))
    }

    fun resolverProblema(n: Int, m: Int, mathCallback: MathCallback) {
        mathCallback.Mathsuccessed(n + m)
        mathCallback.Mathfailed(NumberFormatException("suma incorrecta"), codERROR)
    }

    fun resolverObject(`object`: Any?, objectCallback: ObjectCallback) {
        if (`object` != null) objectCallback.successed(`object`) else objectCallback.failed(NullPointerException("Referencia Vacia"))
    }

    fun resolverAbstract(dato: Int, name: String, abstractCallback: AbstractCallback) {
        if (name.isNotEmpty()) abstractCallback.successed(codOK,
                Model().setNumero(dato).setCifras(floatArrayOf(name.toFloat()))) else abstractCallback.failed(NoClassDefFoundError("Clase no encontrada"), codERROR)
    }

    fun getParsedObject(code: String, methodCall: MethodCall) {
        if (code.isEmpty()) methodCall.onFail("Codido no existe") else {
            try {
                val cod = code.toInt()
                methodCall.onSuccess(Model()
                        .setNumero(cod)
                        .setCifras(floatArrayOf(cod.toFloat())))
            } catch (e: Exception) {
                methodCall.onParse((e as ParseException))
            }
        }
    }

    fun getHttp(url: String?, httpCall: HttpCall<Model?>) {
        httpCall.errorHTTP("404", HttpRetryException("", 404))
        httpCall.onResponse(Model().setCifras(null).setNumero(404))
    }

    fun twoSteps(vararg callbacks: Callback) {
        for (callback in callbacks) {
            (callback as? PreCallback)?.successed("Pre-Success")
                    ?: (callback as? PosCallback)?.failed("Pos-Fail")
        }
    }

    fun returnTripleCallback(i: Int, function: (Int) -> Unit) {
        function(i * 3)
    }

    abstract class MethodCall : SuccessCallback, FailCallback, ParseCallback
    abstract class HttpCall<L> : ResponseObjectCallback<L>, ResponseFailCallback
    companion object : SingletonHolder<MyController>(::MyController) {
        private var instance: MyController? = null
        private const val codOK = 0
        private const val codERROR = -1
    }

}