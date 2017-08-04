package com.mauricio.ifpb.pdm.saogastao;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Produto implements Serializable, Comparable<Produto>{
    private String descricao;
    private float quantidade = 0;
    private float valorUnitario = Float.parseFloat("0.0");
    private Bitmap imgBitmap = null;
    private Date dataHora;

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

        this.dataHora = new Date();
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

    public Bitmap getImgBitmap(){
        return this.imgBitmap;
    }

    public void setImgBitmap(Bitmap img){
        this.imgBitmap = img;
    }

    public String getDataHoraStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM 'às' HH:mm'h'");
        return sdf.format(dataHora);
    }
    public Date getDataHora(){
        return this.dataHora;
    }

    @Override
    public String toString(){
        return this.descricao + ", $un: "+this.valorUnitario+", tot: "+this.getTotal();
    }

    @Override
    public int compareTo(@NonNull Produto o) {
        return this.descricao.compareToIgnoreCase(o.descricao);
    }
}
