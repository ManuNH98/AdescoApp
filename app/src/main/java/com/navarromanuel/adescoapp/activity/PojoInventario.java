package com.navarromanuel.adescoapp.activity;

public class PojoInventario {

    private String producto, cantidad, notas, Uid;

    //int tlf;

    public PojoInventario() {
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    @Override
    public String toString() {
        return "PojoInventario{" +
                "nombre='" + producto + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", notas='" + notas + '\'' +
                ", Uid='" + Uid + '\'' +
                '}';
    }
}