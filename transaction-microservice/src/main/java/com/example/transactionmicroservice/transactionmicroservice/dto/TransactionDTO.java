package com.example.transactionmicroservice.transactionmicroservice.dto;

import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;

import java.util.Date;

public class TransactionDTO {

    private int id;
    private OperationType operationType;
    private double amount;
    private Date date;
    private int accountId;
    private String causal;


    public TransactionDTO(){}

    public TransactionDTO(OperationType operationType, double amount, Date date, int accountId, String causal) {
        this.operationType = operationType;
        this.amount = amount;
        this.date = date;
        this.accountId = accountId;
        this.causal = causal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
