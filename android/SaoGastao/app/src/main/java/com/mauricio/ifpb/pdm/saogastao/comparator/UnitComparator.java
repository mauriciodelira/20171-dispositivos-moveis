package com.mauricio.ifpb.pdm.saogastao.comparator;


import com.mauricio.ifpb.pdm.saogastao.Produto;

import java.util.Comparator;

public class UnitComparator implements Comparator<Produto> {
    @Override
    public int compare(Produto o1, Produto o2) {
        int val = 0;

        if (o1.getValorUnitario() < o2.getValorUnitario()) {
            val = -1;
        }else if (o1.getValorUnitario() == o2.getValorUnitario()){
            val = 0;
        }else if(o1.getValorUnitario() > o2.getValorUnitario()) {
            val = 1;
        }
        return val;
    }
}
