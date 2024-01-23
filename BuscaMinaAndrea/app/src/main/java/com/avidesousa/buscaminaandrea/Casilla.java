package com.avidesousa.buscaminaandrea;

import android.content.Context;
import android.widget.Button;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;


public class Casilla extends Button {
    public int posiFila;
    public int posiColumna;
    public boolean mina;
    public int numeros;

    public Casilla(Context context, int posiFila, int posiColumna, boolean mina) {
        super(context);
        this.posiFila = posiFila;
        this.posiColumna = posiColumna;
        this.mina = mina;
        this.numeros=0;
    }

    public int getPosiColumna() {
        return posiColumna;
    }

    public void setPosiColumna(int posiColumna) {
        this.posiColumna = posiColumna;
    }

    public int getPosiFila() {
        return posiFila;
    }

    public void setPosiFila(int posiFila) {
        this.posiFila = posiFila;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }
}
