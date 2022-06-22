package com.madalore.accountmicroserviceML.dto;

public class AmountMessageDTO {

    private double amount;
    private int accountId;

    public AmountMessageDTO(){}

    public AmountMessageDTO(double amount, int accountId) {
        this.amount = amount;
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
