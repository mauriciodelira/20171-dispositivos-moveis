package com.mauricio.ifpb.pdm.saogastao;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mblf_ on 08/06/2017.
 */

public class ProdutoAdapter extends ArrayAdapter<Produto> {
    private List<Produto> produtos;
    private Activity context;


    private static class ViewHolder {
        public TextView tvHolderDescricao;
        public TextView tvHolderQuantidade;
        public TextView tvHolderValor;
     }

    public ProdutoAdapter(Activity context, List<Produto> produtos) {
        super(context, R.layout.list_item_produto, produtos);
        this.context = context;
        this.produtos = produtos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View linhaParaPopular = convertView;
        ViewHolder holder;
        if( linhaParaPopular == null ){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            linhaParaPopular = inflater.inflate(R.layout.list_item_produto, null);

            holder = new ViewHolder();
            holder.tvHolderDescricao = (TextView) linhaParaPopular.findViewById(R.id.tvListaItemDescricao);
            holder.tvHolderQuantidade = (TextView) linhaParaPopular.findViewById(R.id.tvListaItemQuantidade);
            holder.tvHolderValor= (TextView) linhaParaPopular.findViewById(R.id.tvListaItemValorUnitario);

            linhaParaPopular.setTag(holder);
        }else
            holder = (ViewHolder) linhaParaPopular.getTag();

        holder.tvHolderQuantidade.setText(
                String.format(
                        this.produtos.get(position).getQuantidade() > 1 ? "%s itens" : "%s itenm",
                        this.produtos.get(position).getQuantidade()
                )
        );
        holder.tvHolderDescricao.setText(this.produtos.get(position).getDescricao());
        holder.tvHolderValor.setText(String.format("R$%s",this.produtos.get(position).getValorUnitario()));

        return linhaParaPopular;
    }

    public synchronized void refreshList(List<Produto> produtosAtualizados){
        this.produtos.clear();
        this.produtos.addAll(produtosAtualizados);
        notifyDataSetChanged();
    }

}
