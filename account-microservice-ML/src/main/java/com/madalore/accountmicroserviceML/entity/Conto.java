package com.madalore.accountmicroserviceML.entity;

import javax.persistence.*;

@Entity
@Table(name = "conto")
public class Conto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "numero_conto")
    private String numeroConto;

    @Column(name = "iban")
    private String iban;

    @Column(name = "stato")
    private String stato;


    @Column(name = "saldo")
    private double saldo;


    @JoinColumn(name = "utente_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    private Utente utente;

    public Conto() {
    }

    public Conto(String iban, String stato, double saldo, Utente utente, String numeroConto) {
        this.numeroConto = numeroConto;
        this.iban = iban;
        this.stato = stato;
        this.saldo = saldo;
        this.utente = utente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getNumeroConto() {
        return numeroConto;
    }

    public void setNumeroConto(String numeroConto) {
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
