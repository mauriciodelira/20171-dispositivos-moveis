package com.mauricio.ifpb.pdm.saogastao;

import java.io.Serializable;

public class Produto implements Serializable{
    private String descricao;
    private int quantidade = 0;
    private float valorUnitario = Float.parseFloat("0.0");

    public Produto(String descricao, int quantidade, float valorUnitario){
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }
    public Produto(String descricao, int quantidade){
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = Float.parseFloat("0.00");
    }
    public Produto(String descricao){
        this.descricao = descricao;
        this.quantidade = 0;
        this.valorUnitario = Float.parseFloat("0.00");
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
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
        return this.descricao;
    }

}
