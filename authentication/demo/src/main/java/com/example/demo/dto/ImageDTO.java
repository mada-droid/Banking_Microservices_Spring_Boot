package com.example.demo.dto;

import com.example.demo.entity.User;

public class ImageDTO {

    private int id;
    private String name;
    private byte[] image;
    private UserDTO userDTO;
    private String type;

    public ImageDTO() {
    }

    public ImageDTO(String name, byte[] image, UserDTO userDTO, String type) {
        this.name = name;
        this.image = image;
        this.userDTO = userDTO;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
