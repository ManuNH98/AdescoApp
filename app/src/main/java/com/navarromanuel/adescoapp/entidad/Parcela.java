package com.navarromanuel.adescoapp.entidad;

public class Parcela {
    private String id, nombre, info, metros, tipo, fechainicio, fechafin;
    private int imagenid;

    Parcela(){}

    public Parcela(String id, String nombre, String info, String metros, String tipo, String fechainicio, String fechafin, int imagenid) {
        this.id = id;
        this.nombre = nombre;
        this.info = info;
        this.metros = metros;
        this.tipo = tipo;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.imagenid = imagenid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public int getImagenid() {
        return imagenid;
    }

    public void setImagenid(int imagenid) {
        this.imagenid = imagenid;
    }

    @Override
    public String toString() {
        return "Parcela{" +
                "nombre='" + nombre + '\'' +
                ", info='" + info + '\'' +
                ", metros='" + metros + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechainicio='" + fechainicio + '\'' +
                ", fechafin='" + fechafin + '\'' +
                ", imagenid=" + imagenid +
                ", id=" + id +
                '}';
    }
}
