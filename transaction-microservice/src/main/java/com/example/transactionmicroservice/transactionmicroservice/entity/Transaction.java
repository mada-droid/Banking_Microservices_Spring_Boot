package com.example.transactionmicroservice.transactionmicroservice.entity;

import javax.persistence.*;

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
    private String data;

    @JoinColumn(name = "account_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH})
    private BankAccount bankAccount;

    public Transaction() {
    }

    public Transaction(OperationType operationType, double amount,
                       String data, BankAccount bankAccount) {
        this.operationType = operationType;
        this.amount = amount;
        this.data = data;
        this.bankAccount = bankAccount;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
