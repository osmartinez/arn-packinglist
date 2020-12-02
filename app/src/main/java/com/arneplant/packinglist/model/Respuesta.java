package com.arneplant.packinglist.model;

import java.io.Serializable;

public class Respuesta implements Serializable {
    private short codigo;
    private String mensaje;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(short codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}