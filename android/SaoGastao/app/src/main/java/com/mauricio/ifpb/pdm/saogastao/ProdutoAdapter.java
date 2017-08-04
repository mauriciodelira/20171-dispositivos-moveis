package com.mauricio.ifpb.pdm.saogastao;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class ProdutoAdapter extends BaseAdapter {
    private CrudProdutos crudProd = CrudProdutos.getInstance();
    private Context context;


    private static class ViewHolder {
        public TextView tvHolderDescricao;
        public TextView tvHolderQuantidade;
        public TextView tvHolderValor;
        public TextView tvHolderTotal;
        public ImageView ivHolderThumbnail;
    }

    public ProdutoAdapter(Context context) {
        this.context = context;
    }

    public int getCount(){
        return this.crudProd.getProdutos().size();
    }

    @Override
    public Object getItem(int position) {
        return this.crudProd.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View layout = convertView;
        ViewHolder holder = null;
        Produto prod = this.crudProd.get(position);

        if(layout == null){
            Log.i("HOLDER", "convertView (layout) == null, vai criar um holder");
//            Inflater vai pegar o serviço "inflador de layouts" do Android
            LayoutInflater li = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            A view precisará do layout que irá inflar (no caso, list_item_produto)
            layout = li.inflate(R.layout.list_item_produto, null);

            holder = new ViewHolder();
            holder.tvHolderDescricao = (TextView) layout.findViewById(R.id.tvListaItemDescricao);
            holder.tvHolderQuantidade = (TextView) layout.findViewById(R.id.tvListaItemQuantidade);
            holder.tvHolderValor= (TextView) layout.findViewById(R.id.tvListaItemValorUnitario);
            holder.tvHolderTotal = (TextView) layout.findViewById(R.id.tvListaItemValorTotal);
            holder.ivHolderThumbnail = (ImageView) layout.findViewById(R.id.ivListaItemThumbnail);
            layout.setTag(holder);
        }else
            holder = (ViewHolder) layout.getTag();

        if(prod!=null) {
            holder.tvHolderQuantidade.setText(
                    String.format(Locale.ENGLISH,
                            prod.getQuantidade() > 1 ? "%.0f itens" : "%.0f item",
                            prod.getQuantidade()
                    )
            );
            holder.tvHolderDescricao.setText(prod.getDescricao());
            holder.tvHolderValor.setText(String.format(Locale.ENGLISH, "R$%.2f/un", prod.getValorUnitario()));
            holder.tvHolderTotal.setText(String.format(Locale.ENGLISH, "R$%.2f/tot", prod.getTotal()));
            if(prod.getImgBitmap()!=null){
                holder.ivHolderThumbnail.setImageBitmap(prod.getImgBitmap());
            }
        }else{
            Log.i("PRODUTO", "(ADAPTER) Produto é nulo");
        }

        return layout;
    }


}
