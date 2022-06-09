package com.example.transactionmicroservice.transactionmicroservice.dto;

import com.example.transactionmicroservice.transactionmicroservice.entity.OperationType;

import java.util.Date;

public class TransactionDTO {

    private int id;
    private OperationType operationType;
    private double amount;
    private Date data;
    private int accountId;


    public TransactionDTO(){}

    public TransactionDTO(OperationType operationType, double amount, Date data, int accountId) {
        this.operationType = operationType;
        this.amount = amount;
        this.data = data;
        this.accountId = accountId;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
