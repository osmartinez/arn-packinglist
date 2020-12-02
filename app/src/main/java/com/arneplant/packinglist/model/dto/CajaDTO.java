package com.arneplant.packinglist.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CajaDTO implements Serializable {
    @SerializedName("CodigoEtiqueta")
    private String codigoEtiqueta;
    @SerializedName("Posicion")
    private int posicion;

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
