package com.example.transactionmicroservice.transactionmicroservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(name = "amount")
    private double amount;

    @Column(name = "operation_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "account_id")
    @NotNull(message = "La transizione non è stata assegnata ad un conto!")
    private int accountId;

    @Column(name = "causal")
    private String causal;

    public Transaction() {
    }

    public Transaction(OperationType operationType, double amount, Date date, int accountId, String causal) {
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
