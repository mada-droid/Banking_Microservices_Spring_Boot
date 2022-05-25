package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "conto")
public class Conto {

    @Id
    @Column(name = "numero_conto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroConto;


    @Column(name = "stato")
    private String stato;


    @Column(name = "saldo")
    private double saldo;


    @JoinColumn(name = "utente_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH})
    private Utente utente;

    public Conto() {
    }

    public Conto(String stato, double saldo, Utente utente) {
        this.stato = stato;
        this.saldo = saldo;
        this.utente = utente;
    }

    public int getNumeroConto() {
        return numeroConto;
    }

    public void setNumeroConto(int numeroConto) {
        this.numeroConto = numeroConto;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Conto{" +
                "numeroConto=" + numeroConto +
                ", stato=" + stato +
                ", saldo=" + saldo +
                ", utente=" + utente +
                '}';
    }
}
