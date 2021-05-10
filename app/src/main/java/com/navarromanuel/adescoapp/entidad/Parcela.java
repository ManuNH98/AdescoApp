package com.navarromanuel.adescoapp.entidad;

import java.io.Serializable;

public class Parcela implements Serializable {
    private String nombre, info, metros;
    private int imagenid;

    public Parcela(){}

    public Parcela(String nombre, String info, String metros, int imagenid) {
        this.nombre = nombre;
        this.info = info;
        this.metros = metros;
        this.imagenid = imagenid;
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

    public String getMetros() {
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
