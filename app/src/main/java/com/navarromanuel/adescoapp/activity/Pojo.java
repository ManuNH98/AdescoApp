package com.navarromanuel.adescoapp.activity;

public class Pojo {

    private String nombre, apellido, nif, email, Uid, nombreEmpresa,
            direccionEmpresa, codigoPostal, telefonoEmpresa, ciudadEmpresa, cif,
            provinciaEmpresa, registroNacinal, regitroAutonomico;

    //int tlf;

    public Pojo() {
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getCiudadEmpresa() {
        return ciudadEmpresa;
    }

    public void setCiudadEmpresa(String ciudadEmpresa) {
        this.ciudadEmpresa = ciudadEmpresa;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getProvinciaEmpresa() {
        return provinciaEmpresa;
    }

    public void setProvinciaEmpresa(String provinciaEmpresa) {
        this.provinciaEmpresa = provinciaEmpresa;
    }

    public String getRegistroNacinal() {
        return registroNacinal;
    }

    public void setRegistroNacinal(String registroNacinal) {
        this.registroNacinal = registroNacinal;
    }

    public String getRegitroAutonomico() {
        return regitroAutonomico;
    }

    public void setRegitroAutonomico(String regitroAutonomico) {
        this.regitroAutonomico = regitroAutonomico;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                ", Uid='" + Uid + '\'' +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", direccionEmpresa='" + direccionEmpresa + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", telefonoEmpresa='" + telefonoEmpresa + '\'' +
                ", ciudadEmpresa='" + ciudadEmpresa + '\'' +
                ", cif='" + cif + '\'' +
                ", provinciaEmpresa='" + provinciaEmpresa + '\'' +
                ", registroNacinal='" + registroNacinal + '\'' +
                ", regitroAutonomico='" + regitroAutonomico + '\'' +
                '}';
    }
}