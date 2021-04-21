package com.navarromanuel.adescoapp.entidades;

import java.io.Serializable;

public class Parcela implements Serializable {
    private String nombre, info, metros;
    private int imagenid;

    public Parcela(){}

    public Parcela(String nombre, String info, int imagenid, String metros) {
        this.nombre = nombre;
        this.info = info;
        this.imagenid = imagenid;
        this.metros = metros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMetros(){
        return metros;
    }

    public void setMetros(String metros) {
        this.metros = metros;
    }

    public int getImagenid() {
        return imagenid;
    }

    public void setImagenid(int imagenid) {
        this.imagenid = imagenid;
    }
}

