package com.romellfudi.callbacks

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.romellfudi.callbacks.MyController.HttpCall
import com.romellfudi.callbacks.MyController.MethodCall
import java.text.ParseException

class MainActivity : AppCompatActivity() {
    var txt: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = findViewById<View>(R.id.txt) as TextView
    }

    fun Callback(view: View?) {
        MyController.getInstance().guardarData(txt!!.text.toString(),
                object : Callback {
                    override fun successed(vararg objects: Any) {
                        show(objects[0] as String)
                        txt!!.append(objects[1].toString())
                    }

                    override fun failed(vararg objects: Any) {
                        show(objects[0] as String)
                    }
                })
    }

    fun EnviarCallback(view: View?) { //sendCallback
        MyController.getInstance().enviarData(2, floatArrayOf(0f, 8f), object : EnviarCallback {
            override fun errorAlParsear(exception: Exception?) {
                if (exception != null) show(exception.message)
            }

            override fun successed(vararg objects: Any) {
                show(objects[0].toString())
            }

            override fun failed(vararg objects: Any) {
                show(objects[0].toString())
            }
        })
    }

    fun RestCallBack(view: View?) {
        MyController.getInstance().enviarData(0, floatArrayOf())
                ?.enqueRest(object : RestCallBack {
                    override fun httpOK(vararg objects: Any?) {
                        val num = objects[0] as Int
                        val floats = objects[1] as FloatArray
                        val model = objects[2] as Model
                    }

                    override fun errorHttp(exception: Exception?, vararg objects: Any?) {
                        if (exception == null) show(exception!!.message)
                    }

                    override fun successed(vararg objects: Any) {
                        show(objects[0].toString())
                    }

                    override fun failed(vararg objects: Any) {
                        show(objects[0].toString())
                    }
                })
    }

    fun MathCallback(view: View?) { //MathCallback
        MyController.getInstance().resolverProblema(3, 4, object : MathCallback() {
            override fun Mathsuccessed(vararg objects: Any) {
                show(objects[0].toString())
            }

            override fun Mathfailed(exception: NumberFormatException, vararg objects: Any?) {
                show(exception.message)
            }
        })
    }

    fun ObjectCallback(view: View?) { //        ObjectCallback
        MyController.getInstance().resolverObject(null, object : ObjectCallback() {
            override fun successed(vararg objects: Any) {
                show(objects[0].toString())
            }

            override fun failed(vararg objects: Any) {
                show(objects[0].toString())
            }
        })
    }

    fun AbstractCallback(view: View?) { //        AbstractCallback
        MyController.getInstance().resolverAbstract(3, "34", object : AbstractCallback() {
            override fun successed(vararg objects: Any) {
                show(objects[0].toString())
            }

            override fun failed(exception: Exception?, vararg objects: Any?) {
                if (exception != null) show(exception.message)
            }
        })
    }

    fun MethodParse(view: View?) {
        MyController.getInstance().getParsedObject("", object : MethodCall() {
            override fun onFail(vararg objects: Any) {
                objects[0].toString()
            }

            override fun onParse(exception: ParseException, vararg objects: Any?) {
                show(exception.message)
            }

            override fun onSuccess(vararg objects: Any) {
                objects[0].toString()
            }
        })
    }

    fun OtherMethodParse(view: View?) {
        MyController.getInstance().getHttp("", object : HttpCall<Model?>() {
            override fun errorHTTP(codeError: String, exception: Exception, vararg objects: Any) {
                show(codeError + " - " + exception.message + " - " + objects[0].toString())
            }

            override fun onResponse(model: Model?, vararg objects: Any) {
                show(objects[0].toString())
            }

        })
    }

    fun twoSteps(view: View?) {
        MyController.getInstance().twoSteps(object : PreCallback {
            override fun successed(vararg objects: Any) {
                show(objects[0].toString())
            }

            override fun failed(vararg objects: Any) {
                show(objects[0].toString())
            }
        }, object : PosCallback {
            override fun successed(vararg objects: Any) {
                show(objects[0].toString())
            }

            override fun failed(vararg objects: Any) {
                show(objects[0].toString())
            }
        })
    }

    fun interfaces(view: View?) {
        TemplateController<Model, Model2>()
                .methodStatickCall(object : TemplateController.TemplateCallback<Model?, Model2?>() {
                    override fun methodPoorReturnY(x: Model?): Model2? {
                        return Model2()
                    }

                    override fun methodSuperReturnA(b: Model2?): Model? {
                        return Model()
                    }

                })
    }

    fun returnCallback(view: View?) {
        val value =3
         MyController.getInstance().returnTripleCallback(value) {
             show("The triple of $value is $it")
         }
    }

    private fun show(string: String?) {
        Toast.makeText(this@MainActivity, string, Toast.LENGTH_SHORT).show()
    }
}