package com.example.demo.dto;

import com.example.demo.entity.User;

public class ImageDTO {

    private int id;
    private String name;
    private byte[] image;
    private UserDTO userDTO;

    public ImageDTO(String name, byte[] image, UserDTO userDTO) {
        this.name = name;
        this.image = image;
        this.userDTO = userDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
