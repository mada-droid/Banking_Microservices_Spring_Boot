package com.example.demo.dto;

import com.example.demo.entity.Authority;
import com.example.demo.entity.AuthorityEnum;

import java.util.List;

public class AuthorityListDTO {

    private int id;
    private UserDTO userDTO;
    private List<Authority> authority;

    public AuthorityListDTO() {
    }

    public AuthorityListDTO(List<Authority> authority,UserDTO userDTO) {
        this.userDTO = userDTO;
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(List<Authority> authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthorityListDTO{" +
                "id=" + id +
                ", userDTO=" + userDTO +
                ", authority=" + authority +
                '}';
    }
}
