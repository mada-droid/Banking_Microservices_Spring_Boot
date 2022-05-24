package com.example.demo.entity;

import java.util.List;


public class Operazione {

    private int id;

    private String tipologia;

    private double importo;

    private String data;

    private Conto conto;

    public Operazione() {
    }

    public Operazione(String tipologia, double importo, String data, Conto conto) {
        this.tipologia = tipologia;
        this.importo = importo;
        this.data = data;
        this.conto = conto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Conto getConto() {
        return conto;
    }

    public void setConto(Conto conto) {
        this.conto = conto;
    }
}
