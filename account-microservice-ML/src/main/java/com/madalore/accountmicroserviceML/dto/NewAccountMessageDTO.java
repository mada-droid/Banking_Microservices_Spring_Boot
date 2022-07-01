package com.madalore.accountmicroserviceML.dto;

public class NewAccountMessageDTO {

    private int userId;

    public NewAccountMessageDTO() {
    }

    public NewAccountMessageDTO(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
