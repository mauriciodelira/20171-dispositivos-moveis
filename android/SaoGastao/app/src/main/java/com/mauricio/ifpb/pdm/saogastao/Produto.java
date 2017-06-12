package com.mauricio.ifpb.pdm.saogastao;

import java.io.Serializable;

public class Produto implements Serializable{
    private String descricao;
    private float quantidade = 0;
    private float valorUnitario = Float.parseFloat("0.0");

    public Produto(String descricao, float quantidade, float valorUnitario){
        this.descricao = descricao;
        if(quantidade!=Float.valueOf("0.0"))
            this.quantidade = quantidade;
        else
            this.quantidade = 0;

        if(valorUnitario!=Float.valueOf("0.0"))
            this.valorUnitario = valorUnitario;
        else
            this.valorUnitario = 0;
    }
    public Produto(String descricao, float quantidade){
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = Float.parseFloat("0.00");
    }
    public Produto(String descricao){
        this.descricao = descricao;
        this.quantidade = 0;
        this.valorUnitario = Float.parseFloat("0.00");
    }

    public Produto() {
        this.descricao = "";
        this.quantidade = 0;
        this.valorUnitario = 0;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public float getTotal(){
        return this.valorUnitario * this.quantidade;
    }

    @Override
    public String toString(){
        return this.descricao + ", $un: "+this.valorUnitario+", tot: "+this.getTotal();
    }

}
