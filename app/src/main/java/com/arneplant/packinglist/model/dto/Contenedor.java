package com.arneplant.packinglist.model.dto;

import java.util.Date;

public class Contenedor {
    private String CodigoEtiqueta;

    private Short Tipo;

    private String CodUbicacion;

    private Date FechaAsociacion;


    public String getCodigoEtiqueta() {
        return CodigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        CodigoEtiqueta = codigoEtiqueta;
    }

    public Short getTipo() {
        return Tipo;
    }

    public void setTipo(Short tipo) {
        Tipo = tipo;
    }

    public String getCodUbicacion() {
        return CodUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        CodUbicacion = codUbicacion;
    }

    public Date getFechaAsociacion() {
        return FechaAsociacion;
    }

    public void setFechaAsociacion(Date fechaAsociacion) {
        FechaAsociacion = fechaAsociacion;
    }
}
