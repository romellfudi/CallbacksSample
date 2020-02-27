package com.romellfudi.callbacks

/**
 * Created by romelldominguez on 9/19/16.
 */
class Model {
    var numero = 0
    var cifras: FloatArray? = null

    fun setNumero(numero: Int): Model {
        this.numero = numero
        return this
    }

    fun setCifras(cifras: FloatArray?): Model {
        this.cifras = cifras
        return this
    }
}