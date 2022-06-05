package com.madalore.accountmicroserviceML.dto;

import com.madalore.accountmicroserviceML.entity.State;

public class BankAccountDTO {

    private int id;
    private String accountNumber;
    private String iban;
    private State state;
    private double saldo;
    private UserDTO userDTO;

    public BankAccountDTO() {
    }

    public BankAccountDTO(String iban, State state, double saldo, UserDTO userDTO, String accountNumber) {
        this.accountNumber = accountNumber;
        this.iban = iban;
        this.state = state;
        this.saldo = saldo;
        this.userDTO = userDTO;
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "BankAccountDTO{" +
                "accountNumber='" + accountNumber + '\'' +
                ", iban='" + iban + '\'' +
                ", state=" + state +
                ", saldo=" + saldo +
                ", userDTO=" + userDTO +
                '}';
    }
}
