package com.madalore.accountmicroserviceML.dto;

public class TransferResponseDTO {

    private int accountIdFrom;
    private int accountIdTo;
    private int errorMessage;

    public TransferResponseDTO() {
    }

    public TransferResponseDTO(int accountIdFrom, int accountIdTo) {
        this.accountIdFrom = accountIdFrom;
        this.accountIdTo = accountIdTo;
    }

    public int getAccountIdFrom() {
        return accountIdFrom;
    }

    public void setAccountIdFrom(int accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
    }

    public int getAccountIdTo() {
        return accountIdTo;
    }

    public void setAccountIdTo(int accountIdTo) {
        this.accountIdTo = accountIdTo;
    }

    public int getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(int errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "TransferResponseDTO{" +
                "accountIdFrom=" + accountIdFrom +
                ", accountIdTo=" + accountIdTo +
                '}';
    }
}
