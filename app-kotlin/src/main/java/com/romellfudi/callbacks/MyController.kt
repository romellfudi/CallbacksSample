package com.romellfudi.callbacks

import com.romellfudi.callbacks.interfaces.*
import java.net.HttpRetryException
import java.text.ParseException

/**
 * Created by romelldominguez on 9/19/16.
 */
object MyController {
    private const val codOK = 0
    private const val codERROR = -1
    private var mModel: Model? = null

    fun guardarData(data: String, callback: Callback) =
            if (data.isNotEmpty()) callback.successed("Se obtuvo la información", data)
            else callback.failed("Mensaje de Error")

    fun enviarData(num: Int, decimals: FloatArray, enviarCallback: EnviarCallback?): Int {
        return if (num == 0 && decimals.isNotEmpty()) {
            enviarCallback?.successed(codOK)
            codOK
        } else {
            enviarCallback?.errorAlParsear(ParseException("parseo", -1))
            codERROR
        }
    }

    fun enviarData(num: Int, decimals: FloatArray, enviarCallback: EnviarCallback?, restCallBack: RestCallBack) {
        val cod = enviarData(num, decimals, enviarCallback)
        if (cod == codOK) {
            if (enviarCallback == null) {
                restCallBack.failed("Enviar data error")
            } else {
                restCallBack.httpOK("ok")
            }
        } else {
            enviarCallback?.failed("Error al parsear la data de envio")
        }
    }

    fun enviarData(num: Int, decimals: FloatArray?) {
        mModel = Model(num, decimals)
    }

    fun enqueRest(restCallBack: RestCallBack) {
        mModel?.let { restCallBack.httpOK(it.numero, it.cifras, it) }
                ?: restCallBack.errorHttp(NullPointerException("modelo sin construir"))
    }

    fun resolverProblema(n: Int, m: Int, mathCallback: MathCallback) {
        mathCallback.Mathsuccessed(n + m)
        mathCallback.Mathfailed(NumberFormatException("suma incorrecta"), codERROR)
    }

    fun resolverObject(`object`: Any?, objectCallback: ObjectCallback) {
        `object`?.let { objectCallback.successed(it) }
                ?: objectCallback.failed(NullPointerException("Referencia Vacia"))
    }

    fun resolverAbstract(dato: Int, name: String, abstractCallback: AbstractCallback) {
        if (name.isNotEmpty()) abstractCallback.successed(codOK, Model(dato, floatArrayOf(name.toFloat())))
        else abstractCallback.failed(NoClassDefFoundError("Clase no encontrada"), codERROR)
    }

    fun getParsedObject(code: String, methodCall: MethodCall) =
            if (code.isEmpty()) methodCall.onFail("Codido no existe") else {
                try {
                    methodCall.onSuccess(Model(code.toInt(), floatArrayOf(code.toFloat())))
                } catch (e: Exception) {
                    methodCall.onParse((e as ParseException))
                }
            }

    fun getHttp(url: String?, httpCall: HttpCall<Model?>) {
        httpCall.errorHTTP("404", HttpRetryException("", 404))
        httpCall.onResponse(Model(404, null))
    }

    fun twoSteps(vararg callbacks: Callback) {
        for (callback in callbacks)
            (callback as? PreCallback)?.successed("Pre-Success")
                ?: (callback as? PosCallback)?.failed("Pos-Fail")
    }

    fun returnTripleCallback(i: Int, function: (Int, Int) -> Unit) {
        function(i, i * 3)
    }

    abstract class MethodCall : SuccessCallback, FailCallback, ParseCallback
    abstract class HttpCall<L> : ResponseObjectCallback<L>, ResponseFailCallback
}