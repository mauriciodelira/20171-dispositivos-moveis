package com.mauricio.ifpb.pdm.saogastao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CrudProdutos {
    private List<Produto> produtos;
    private static CrudProdutos instance = null;

    private CrudProdutos(){
        this.produtos = new ArrayList<Produto>();
    }

    public List<Produto> getProdutos(){
        return this.produtos;
    }

    public static CrudProdutos getInstance(){
        if(instance == null)
            instance = new CrudProdutos();

        return instance;
    }

    public void add(Produto produto){
        if(produtos==null)
            this.produtos = new ArrayList<Produto>();

        this.produtos.add(produto);

    }

//    TO-DO Implementar
    public void zerar(){
        this.produtos.clear();
    }

    public void remove(Produto p){
        this.produtos.remove(p);
    }

    public float getTotal(){
        float soma = 0;
        for(Produto p : this.produtos){
            soma += p.getTotal();
        }
        return soma;
    }

    public Produto findProduto(Produto prod){
        for (Produto p : this.produtos) {
            if(p.equals(prod))
                return p;
        }
        return null;
    }
    public Produto findProduto(String descricao){
        for (Produto p : this.produtos) {
            if(p.getDescricao().equalsIgnoreCase(descricao))
                return p;
        }
        return null;
    }

    public List<Produto> getCloneProdutos() {
        List<Produto> clone = new ArrayList<>();
        for(Produto p : this.produtos) {
            clone.add(p);
        }
        Log.i("MILHO","CrudProdutos: "+clone.toString());
        return clone;
    }
}
