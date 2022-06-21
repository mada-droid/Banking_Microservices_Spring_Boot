package com.example.demo.dto;

import com.example.demo.entity.Authority;

public class AuthorityListDTO {

    private int id;
    private AuthorityDTO authorityDTO;
    private UserDTO userDTO;

    public AuthorityListDTO(AuthorityDTO authorityDTO, UserDTO userDTO) {
        this.authorityDTO = authorityDTO;
        this.userDTO = userDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorityDTO getAuthorityDTO() {
        return authorityDTO;
    }

    public void setAuthorityDTO(AuthorityDTO authorityDTO) {
        this.authorityDTO = authorityDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "AuthorityListDTO{" +
                "id=" + id +
                ", authorityDTO=" + authorityDTO +
                ", userDTO=" + userDTO +
                '}';
    }
}
