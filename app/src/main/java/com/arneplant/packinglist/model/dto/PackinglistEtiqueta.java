package com.arneplant.packinglist.model.dto;

public class PackinglistEtiqueta {
    private String codigoEtiqueta;
    private int idPackinglist;

    public PackinglistEtiqueta() {
    }

    public PackinglistEtiqueta(String codigoEtiqueta, int idPackinglist) {
        this.codigoEtiqueta = codigoEtiqueta;
        this.idPackinglist = idPackinglist;
    }

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public int getIdPackinglist() {
        return idPackinglist;
    }

    public void setIdPackinglist(int idPackinglist) {
        this.idPackinglist = idPackinglist;
    }
}
