package com.arneplant.packinglist.model.dto;

public class Mensaje {
    private int tipo;
    private String ipDestinatario;
    private String ipEmisor;
    private String cuerpo;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getIpDestinatario() {
        return ipDestinatario;
    }

    public void setIpDestinatario(String ipDestinatario) {
        this.ipDestinatario = ipDestinatario;
    }

    public String getIpEmisor() {
        return ipEmisor;
    }

    public void setIpEmisor(String ipEmisor) {
        this.ipEmisor = ipEmisor;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}
