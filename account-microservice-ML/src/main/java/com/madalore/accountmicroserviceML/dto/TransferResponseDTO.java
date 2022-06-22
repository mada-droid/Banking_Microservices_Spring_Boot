package com.madalore.accountmicroserviceML.dto;

public class TransferResponseDTO {

    private int accountIdFrom;
    private int accountIdTo;

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


    @Override
    public String toString() {
        return "TransferResponseDTO{" +
                "accountIdFrom=" + accountIdFrom +
                ", accountIdTo=" + accountIdTo +
                '}';
    }
}
