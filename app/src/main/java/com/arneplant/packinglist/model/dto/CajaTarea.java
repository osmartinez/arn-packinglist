package com.arneplant.packinglist.model.dto;

public class CajaTarea {
    public String CodigoEtiqueta;
    public int IdStock;
    public int IdStockTalla;
    public int IdArticulo;
    public String CodigoArticulo;
    public int IdOrdenFabricacion;
    public int IdOperacion;
    public int IdTarea;
    public String  Talla;
    public String TallaUtillaje;
    public double Cantidad;

    public CajaTarea() {
    }

    public String getCodigoEtiqueta() {
        return CodigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        CodigoEtiqueta = codigoEtiqueta;
    }

    public int getIdStock() {
        return IdStock;
    }

    public void setIdStock(int idStock) {
        IdStock = idStock;
    }

    public int getIdStockTalla() {
        return IdStockTalla;
    }

    public void setIdStockTalla(int idStockTalla) {
        IdStockTalla = idStockTalla;
    }

    public int getIdArticulo() {
        return IdArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        IdArticulo = idArticulo;
    }

    public String getCodigoArticulo() {
        return CodigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        CodigoArticulo = codigoArticulo;
    }

    public int getIdOrdenFabricacion() {
        return IdOrdenFabricacion;
    }

    public void setIdOrdenFabricacion(int idOrdenFabricacion) {
        IdOrdenFabricacion = idOrdenFabricacion;
    }

    public int getIdOperacion() {
        return IdOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        IdOperacion = idOperacion;
    }

    public int getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(int idTarea) {
        IdTarea = idTarea;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String talla) {
        Talla = talla;
    }

    public String getTallaUtillaje() {
        return TallaUtillaje;
    }

    public void setTallaUtillaje(String tallaUtillaje) {
        TallaUtillaje = tallaUtillaje;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double cantidad) {
        Cantidad = cantidad;
    }
}
