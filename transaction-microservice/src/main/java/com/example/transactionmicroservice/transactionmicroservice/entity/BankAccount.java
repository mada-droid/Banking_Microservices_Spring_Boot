package com.example.transactionmicroservice.transactionmicroservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "state")
    private String state;

    @Column(name = "iban")
    private String iban;

    @Column(name = "balance")
    private double balance;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH})
    private User user;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, String state, String iban, double balance, User user) {
        this.accountNumber = accountNumber;
        this.state = state;
        this.iban = iban;
        this.balance = balance;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Conto{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", state='" + state + '\'' +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}
