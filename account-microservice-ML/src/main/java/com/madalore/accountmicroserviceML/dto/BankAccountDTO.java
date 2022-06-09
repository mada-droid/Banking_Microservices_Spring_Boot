package com.madalore.accountmicroserviceML.dto;

import com.madalore.accountmicroserviceML.entity.State;

public class BankAccountDTO {

    private int id;
    private String accountNumber;
    private String iban;
    private State state;
    private double balance;
    private int userId;

    public BankAccountDTO() {
    }

    public BankAccountDTO(String iban, State state, double balance, int userId, String accountNumber) {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
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
        return "BankAccountDTO{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", iban='" + iban + '\'' +
                ", state=" + state +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }
}
