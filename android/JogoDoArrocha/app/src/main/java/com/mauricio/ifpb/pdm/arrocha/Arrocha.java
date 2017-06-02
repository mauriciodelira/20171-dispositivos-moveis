package com.mauricio.ifpb.pdm.arrocha;

import android.widget.Toast;

import java.util.Random;

/**
 * isArrochado
 * validar
 * jogar(chute)
 */

public class Arrocha {
    private int menor, maior, secreto;

    public Arrocha(){
        this.menor = 1;
        this.maior = 100;
        this.secreto = new Random().nextInt(98)+2;
    }

    // Se o chute está dentro do intervalo
    private boolean validar(int n){
        return (n >= this.menor  && n <= this.maior);
    }

    // Apenas para o uso do Toast
    public boolean isMaior(int n){
        return (n > this.maior);
    }
    public boolean isMenor(int n){
        return (n < this.menor);
    }

    public void reset(){
        this.maior = 100;
        this.menor = 1;
        this.secreto = new Random().nextInt(98)+2;
    }

    // Joga um número
    protected int jogar(int n) {
        if (this.validar(n)) {
            if (n < this.secreto) {
                this.menor = n;
            } else if (n > this.secreto) {
                this.maior = n;
            }
            if (this.isArrochado() == true){
                return 0;
            }
            if(n == this.secreto){
                return 1;
            }
//            Só fez alterar os valores de maior ou menor
            return -2;
        } else
            return -1;
    }


    private boolean isArrochado(){
        return this.menor + 1 == this.maior - 1;
    }

    public int getMaior(){
        return this.maior;
    }

    public int getMenor(){
        return this.menor;
    }

    public int getSecreto(){
        return this.secreto;
    }

    public String toString(){
        return ("[menor: "+this.menor+", maior: "+this.maior+" | secreto: "+this.secreto+"] | isArrochado? "+this.isArrochado()+"\n");
    }

}
