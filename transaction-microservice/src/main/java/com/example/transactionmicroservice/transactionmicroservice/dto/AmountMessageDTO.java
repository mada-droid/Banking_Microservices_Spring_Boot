package com.example.transactionmicroservice.transactionmicroservice.dto;

import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;

public class AmountMessageDTO {

    private double amount;
    private int accountId;
    private String causal;

    public AmountMessageDTO(){}

    public AmountMessageDTO(double amount, int accountId, String causal) {
        this.amount = amount;
        this.accountId = accountId;
        this.causal = causal;
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

    public String getCausal() {
        return causal;
    }

    public void setCausal(String causal) {
        this.causal = causal;
    }
}
