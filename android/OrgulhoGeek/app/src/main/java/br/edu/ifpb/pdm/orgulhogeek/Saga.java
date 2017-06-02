package br.edu.ifpb.pdm.orgulhogeek;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mblf_ on 26/05/2017.
 */

public class Saga implements Serializable{
    private String name;
    private String description;
    private float rating;
    private int imageLocal;
    private List<Comentario> comentarios = new ArrayList<>();

    public Saga(){}

    public Saga(String name, String description, int image){
        this.name = name;
        this.description = description;
        this.rating = 0;
        this.imageLocal = image;
        if(this.comentarios == null) comentarios = new ArrayList<>();
    }

    public Saga(String name, String description, int image, double rate){
        this.name = name;
        this.description = description;
        this.rating = (float) rate;
        this.imageLocal = image;
        if(this.comentarios == null) comentarios = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }

    public int getImageLocal() {
        return imageLocal;
    }

    public void addComentario(String comentario){
        comentarios.add(new Comentario(this, comentario));
    }

    public String toString(){
        return this.name;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }
}
