package com.madalore.accountmicroserviceML.dao;

import com.madalore.accountmicroserviceML.entity.Utente;

import javax.persistence.*;

public class ContoDTO {

    private String numeroConto;
    private String iban;
    private String stato;
    private double saldo;
    private Utente utente;

    public ContoDTO() {
    }

    public ContoDTO(String numeroConto, String iban, String stato, double saldo, Utente utente) {
        this.numeroConto = numeroConto;
        this.iban = iban;
        this.stato = stato;
        this.saldo = saldo;
        this.utente = utente;
    }

    public String getNumeroConto() {
        return numeroConto;
    }

    public void setNumeroConto(String numeroConto) {
        this.numeroConto = numeroConto;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
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

    public void setUtenteDTO(Utente utente) {
        this.utente = utente;
    }
}
