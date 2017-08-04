package com.mauricio.ifpb.pdm.saogastao.comparator;

import com.mauricio.ifpb.pdm.saogastao.Produto;

import java.util.Comparator;


public class DataComparator implements Comparator<Produto> {
    @Override
    public int compare(Produto o1, Produto o2) {
        return o1.getDataHora().compareTo(o2.getDataHora());
    }
}
