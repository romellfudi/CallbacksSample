package com.romellfudi.callbacks

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.romellfudi.callbacks.MyController.HttpCall
import com.romellfudi.callbacks.MyController.MethodCall
import com.romellfudi.callbacks.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (DataBindingUtil.setContentView(this, R.layout.activity_main)
                as ActivityMainBinding).also { it.main = this }
    }

    fun Callback() {
        MyController.guardarData(txt.text.toString(),
                object : Callback {
                    override fun successed(vararg objects: Any) {
                        show(objects[0] as String)
                        txt.append(objects[1].toString())
                    }

                    override fun failed(vararg objects: Any) {
                        show(objects[0] as String)
                    }
                })
    }

    fun EnviarCallback() {
        MyController.enviarData(2, floatArrayOf(0f, 8f), object : EnviarCallback {
            override fun errorAlParsear(exception: Exception?) {
                show(exception?.message ?: "No exception found")
            }

            override fun successed(vararg objects: Any) {
                show(objects[0].toString())
            }

            override fun failed(vararg objects: Any) {
                show(objects[0].toString())
            }
        })
    }

    fun RestCallBack() {
        MyController.apply {
            enviarData(0, floatArrayOf())
            enqueRest(object : RestCallBack {
                override fun httpOK(vararg objects: Any?) {
                    val num = objects[0] as Int
                    val floats = objects[1] as FloatArray
                    val model = objects[2] as Model
                }

                override fun errorHttp(exception: Exception?, vararg objects: Any?) {
                    exception?.let { show(it.message) }
                }

                override fun successed(vararg objects: Any) {
                    show(objects[0].toString())
                }

                override fun failed(vararg objects: Any) {
                    show(objects[0].toString())
                }
            })
        }
    }

    fun MathCallback() {
        MyController.resolverProblema(3, 4, object : MathCallback() {
            override fun Mathsuccessed(vararg objects: Any) {
                show(objects[0].toString())
            }

            override fun Mathfailed(exception: NumberFormatException, vararg objects: Any?) {
                show(exception.message)
            }
        })
    }

    fun ObjectCallback() {
        MyController.resolverObject(null, object : ObjectCallback() {
            override fun successed(vararg objects: Any) {
                show(objects[0].toString())
            }

            override fun failed(vararg objects: Any) {
                show(objects[0].toString())
            }
        })
    }

    fun AbstractCallback() {
        MyController.resolverAbstract(3, "34", object : AbstractCallback() {
            override fun successed(vararg objects: Any) {
                show(objects[0].toString())
            }

            override fun failed(exception: Exception?, vararg objects: Any?) {
                exception?.let { show(it.message) }
            }
        })
    }

    fun MethodParse() {
        MyController.getParsedObject("", object : MethodCall() {
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

    fun OtherMethodParse() {
        MyController.getHttp("", object : HttpCall<Model?>() {
            override fun errorHTTP(codeError: String, exception: Exception, vararg objects: Any) {
                show(codeError + " - " + exception.message + " - " + objects[0].toString())
            }

            override fun onResponse(model: Model?, vararg objects: Any) {
                show(objects[0].toString())
            }

        })
    }

    fun twoSteps() {
        MyController.twoSteps(object : PreCallback {
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

    fun interfaces() {
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

    fun returnCallback() {
        val value = 3
        MyController.returnTripleCallback(value) { it: Int, iTriple: Int ->
            show("The triple of $it is $iTriple")
        }
    }

    private fun show(string: String?) {
        Toast.makeText(this@MainActivity, string, Toast.LENGTH_SHORT).show()
    }
}