package br.edu.ifpb.pdm.orgulhogeek;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by mblf_ on 29/05/2017.
 */


public class CommentsAdapter extends ArrayAdapter {
    private Activity context;
    private List<Comentario> comments;

//    Guarda as Views que vão ser reutilizadas
    private static class ViewHolder {
        TextView comment;
        TextView timestamp;
    }

    public CommentsAdapter(Activity context, List<Comentario> comments){
        super(context, R.layout.layout_list_comments_ui, comments);

        this.context = context;
        this.comments = comments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        Procura ver se ja existe uma viewHolder
        ViewHolder viewHolder; // esteria armazenado em cache (na TAG definida abaixo)

        final View result;

        if(convertView == null ){
            viewHolder = new ViewHolder();

            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_list_comments_ui, parent, false);
            viewHolder.comment = (TextView) convertView.findViewById(R.id.tvListaComentario);
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.tvListaComentarioTimestamp);

            result = convertView;

            // armazenou em cache as views
            convertView.setTag(viewHolder);

            /* ===== OLD WAY
            // Uma row vai inflar com: layout, raiz definida, attach to root?.
            View row = inflater.inflate(R.layout.layout_list_ui, null, true);
            */

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.comment.setText(this.comments.get(position).getComment());
        viewHolder.timestamp.setText(DateFormat.getDateTimeInstance().format( this.comments.get(position).getTime() ) );

//        Retorna a view completa pra renderizar na tela
        return convertView;
    }
}