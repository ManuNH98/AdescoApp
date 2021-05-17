package com.navarromanuel.adescoapp.activity;

public class PojoInventario {

    private String producto, cantidad, notas, titular, fechaAlta, ID, Uid, procedencia;

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

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    @Override
    public String toString() {
        return "PojoInventario{" +
                "producto='" + producto + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", notas='" + notas + '\'' +
                ", titular='" + titular + '\'' +
                ", fechaAlta='" + fechaAlta + '\'' +
                ", idHerramienta='" + ID + '\'' +
                ", Uid='" + Uid + '\'' +
                ", procedencia='" + procedencia + '\'' +
                '}';
    }
}