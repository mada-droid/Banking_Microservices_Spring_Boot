package com.example.demo.dto;

public class UserUpdateEmailDTO {

    private String email;
    private int id;

    public UserUpdateEmailDTO(String email, int id) {
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}