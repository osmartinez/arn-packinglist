package com.arneplant.packinglist.model.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ConsumoCajaSeccionGrupo {
    private ArrayList<ConsumoCajaSeccion> consumos;

    public String CodigoEtiqueta;
    public String Talla;
    public double Cantidad;
    public String CodigoArticulo;
    public int IdStock;
    public int IdStockTalla;
    public String TallaUtillaje;
    public int IdOrden;
    public int IdOperacion;
    public String Cliente;
    public String CodigoOrden;
    public int IdTarea;
    public double ParesRestantes;
    public double ParesFabricar;
    public double ParesFabricados;


    public ConsumoCajaSeccionGrupo() {
        consumos = new ArrayList<>();
    }

    public ConsumoCajaSeccionGrupo(ArrayList<ConsumoCajaSeccion> consumos) {
        this.consumos = consumos;
    }

    public ArrayList<ConsumoCajaSeccionGrupo> generarGrupos(ArrayList<ConsumoCajaSeccion> consumos){
        ArrayList<ConsumoCajaSeccionGrupo> grupos = new ArrayList<>();
        //Function<ConsumoCajaSeccion, List<Object>> compositeKey = c-> Arrays.<Object>asList(c.getCodigoEtiqueta());
        return grupos;
    }

    public ArrayList<ConsumoCajaSeccion> getConsumos() {
        return consumos;
    }

    public String getCodigoEtiqueta() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getCodigoEtiqueta();
        } else {
            return null;
        }
    }

    public String getTalla() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getTalla();
        } else {
            return null;
        }
    }

    public double getCantidad() {
        if (!this.consumos.isEmpty()) {
            double cant = 0;
            for(ConsumoCajaSeccion c: this.consumos){
                cant+=c.getCantidad();
            }
            return cant;
        } else {
            return 0;
        }
    }

    public String getCodigoArticulo() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getCodigoArticulo();
        } else {
            return null;
        }
    }

    public int getIdStock() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getIdStock();
        } else {
            return 0;
        }
    }

    public int getIdStockTalla() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getIdStockTalla();
        } else {
            return 0;
        }
    }

    public String getTallaUtillaje() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getTallaUtillaje();
        } else {
            return null;
        }
    }

    public int getIdOrden() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getIdOrden();
        } else {
            return 0;
        }
    }

    public int getIdOperacion() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getIdOperacion();
        } else {
            return 0;
        }
    }

    public String getCliente() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getCliente();
        } else {
            return null;
        }
    }

    public String getCodigoOrden() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getCodigoOrden();
        } else {
            return null;
        }
    }

    public int getIdTarea() {
        ;
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getIdTarea();
        } else {
            return 0;
        }
    }

    public double getParesRestantes() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getParesRestantes();
        } else {
            return 0;
        }
    }

    public double getParesFabricar() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getParesFabricar();
        } else {
            return 0;
        }
    }

    public double getParesFabricados() {
        if (!this.consumos.isEmpty()) {
            return this.consumos.get(0).getParesFabricados();
        } else {
            return 0;
        }
    }
}
