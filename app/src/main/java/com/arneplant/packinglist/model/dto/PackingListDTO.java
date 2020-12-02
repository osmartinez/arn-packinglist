package com.arneplant.packinglist.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class PackingListDTO implements Serializable {
    @SerializedName("Id")
    private int id;
    @SerializedName("CodigoAgrupacion")
    private String codigoAgrupacion;
    @SerializedName("Observacion")
    private String observacion;
    @SerializedName("FechaCreacion")
    private Date fechaCreacion;
    @SerializedName("Cajas")
    private ArrayList<CajaDTO> cajas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoAgrupacion() {
        return codigoAgrupacion;
    }

    public void setCodigoAgrupacion(String codigoAgrupacion) {
        this.codigoAgrupacion = codigoAgrupacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ArrayList<CajaDTO> getCajas() {
        return cajas;
    }

    public void setCajas(ArrayList<CajaDTO> cajas) {
        this.cajas = cajas;
    }
}
