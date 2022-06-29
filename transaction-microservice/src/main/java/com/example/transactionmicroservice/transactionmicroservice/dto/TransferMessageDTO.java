package com.example.transactionmicroservice.transactionmicroservice.dto;

public class TransferMessageDTO {

    private String originIban;
    private String destinationIban;
    private double amount;
    private String causal;

    public TransferMessageDTO() {
    }

    public TransferMessageDTO(String originIban, String destinationIban, double amount, String causal) {
        this.originIban = originIban;
        this.destinationIban = destinationIban;
        this.amount = amount;
        this.causal = causal;
    }

    public String getOriginIban() {
        return originIban;
    }

    public void setOriginIban(String originIban) {
        this.originIban = originIban;
    }

    public String getDestinationIban() {
        return destinationIban;
    }

    public void setDestinationIban(String destinationIban) {
        this.destinationIban = destinationIban;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCausal() {
        return causal;
    }

    public void setCausal(String causal) {
        this.causal = causal;
    }
}
