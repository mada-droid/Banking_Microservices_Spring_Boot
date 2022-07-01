package com.madalore.accountmicroserviceML.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_number")
    @NotNull(message = "Il conto deve avere un numero conto!")
    private String accountNumber;

    @Column(name = "iban")
    @NotNull(message = "Il conto deve avere un iban!")
    private String iban;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Il conto deve avere uno stato!")
    private State state;

    @Column(name = "balance")
    @NotNull(message = "Il conto deve avere un saldo iniziale!")
    private double balance;

    @Column(name = "user_id")
    @NotNull(message = "Il conto deve essere associato ad un utente!")
    private int userId;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, String iban, State state, double balance, int userId) {
        this.accountNumber = accountNumber;
        this.iban = iban;
        this.state = state;
        this.balance = balance;
        this.userId = userId;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", iban='" + iban + '\'' +
                ", state=" + state +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }
}
