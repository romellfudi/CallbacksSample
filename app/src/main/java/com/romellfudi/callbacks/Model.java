package com.romellfudi.callbacks;

/**
 * Created by romelldominguez on 9/19/16.
 */
public class Model {

    int numero;
    float[] cifras;

    public int getNumero() {
        return numero;
    }

    public Model setNumero(int numero) {
        this.numero = numero;
        return this;
    }

    public float[] getCifras() {
        return cifras;
    }

    public Model setCifras(float[] cifras) {
        this.cifras = cifras;
        return this;
    }
}
