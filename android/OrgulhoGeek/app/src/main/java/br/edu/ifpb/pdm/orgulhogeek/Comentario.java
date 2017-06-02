package br.edu.ifpb.pdm.orgulhogeek;

import java.util.Date;

/**
 * Created by mblf_ on 29/05/2017.
 */

class Comentario {
    private String text;
    private Date timestamp;
    private Saga saga = null;

    public Comentario(String text){
        this.text = text;
        this.timestamp = new Date();
    }

    public Comentario(Saga saga, String text){
        this.text = text;
        this.timestamp = new Date();
        this.saga = saga;
    }

    public Date getTime() {
        return timestamp;
    }

    public String getComment() {
        return text;
    }
}
