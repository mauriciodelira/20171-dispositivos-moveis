package com.mauricio.ifpb.pdm.saogastao;

import java.util.ArrayList;
import java.util.List;

public class CadastroProdutos {
    private List<Produto> produtos;

    public CadastroProdutos(){
        this.produtos = new ArrayList<Produto>();
    }

    public void add(Produto produto){
        if(produtos==null)
            this.produtos = new ArrayList<Produto>();

        this.produtos.add(produto);

    }

//    TO-DO Implementar
    public void remove(){}

    public float getTotal(){
        float soma = 0;
        for(Produto p : this.produtos){
            soma += p.getTotal();
        }
        return soma;
    }

    
}
