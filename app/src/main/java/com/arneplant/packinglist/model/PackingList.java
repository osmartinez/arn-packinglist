package com.arneplant.packinglist.model;

import java.util.Date;

public class PackingList {
    private int Id;

    private String UsuarioCreacion;

    private String Observacion;

    private String CodAgrupacion;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsuarioCreacion() {
        return UsuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        UsuarioCreacion = usuarioCreacion;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public String getCodAgrupacion() {
        return CodAgrupacion;
    }

    public void setCodAgrupacion(String codAgrupacion) {
        CodAgrupacion = codAgrupacion;
    }
}

