package com.mauricio.ifpb.pdm.saogastao;

import com.mauricio.ifpb.pdm.saogastao.comparator.DataComparator;
import com.mauricio.ifpb.pdm.saogastao.comparator.DescComparator;
import com.mauricio.ifpb.pdm.saogastao.comparator.QuantidadeComparator;
import com.mauricio.ifpb.pdm.saogastao.comparator.TotalComparator;
import com.mauricio.ifpb.pdm.saogastao.comparator.UnitComparator;

import java.util.ArrayList;
import java.util.Collections;
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

    public void sort(){
        Collections.sort(this.produtos);
    }
    public void sortDesc(){
        Collections.sort(this.produtos, new DescComparator());
    }
    public void sortQtd(){
        Collections.sort(this.produtos, new QuantidadeComparator());
    }
    public void sortData(){
        Collections.sort(this.produtos, new DataComparator());
    }
    public void sortTot(){
        Collections.sort(this.produtos, new TotalComparator());
    }
    public void sortUn(){
        Collections.sort(this.produtos, new UnitComparator());
    }

    public void zerar(){
        this.produtos.clear();
    }

    public void remove(int posicao){
        this.produtos.remove(posicao);
    }

    public float getTotal(){
        float soma = 0;
        for(Produto p : this.produtos){
            soma += p.getTotal();
        }
        return soma;
    }

    public Produto get(int posicao){
        return this.produtos.get(posicao);
    }

    public Produto get(Produto prod){
        for (Produto p : this.produtos) {
            if(p.equals(prod))
                return p;
        }
        return null;
    }
}
