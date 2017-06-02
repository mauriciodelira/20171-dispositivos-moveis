package br.edu.ifpb.pdm.orgulhogeek;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mblf_ on 26/05/2017.
 */

public class SagaAdapter extends ArrayAdapter {
    private List<Saga> sagas;
    private Activity context;

    public SagaAdapter(Activity context, List<Saga> sagas){
        super(context, R.layout.layout_list_ui, sagas);

        this.context = context;
        this.sagas = sagas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        // Uma row vai inflar com: layout, raiz definida, attach to root?.
        View row = inflater.inflate(R.layout.layout_list_ui, null, true);

        TextView titulo = (TextView) row.findViewById(R.id.tvListaItemTitulo);
        TextView descricao = (TextView) row.findViewById(R.id.tvListaItemDescricao);
        ImageView poster = (ImageView) row.findViewById(R.id.ivListItemImage);

        titulo.setText(sagas.get(position).getName());
        descricao.setText(sagas.get(position).getDescription());
        poster.setImageResource(sagas.get(position).getImageLocal());

        return row;
    }
}
