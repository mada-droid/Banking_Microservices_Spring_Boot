package com.example.demo.dto;

import com.example.demo.entity.Authority;
import com.example.demo.entity.AuthorityEnum;

import java.util.List;

public class AuthorityDTO {

    private int id;
    private AuthorityEnum authorityEnum;

    public AuthorityDTO() {
    }

    public AuthorityDTO(AuthorityEnum authorityEnum) {
        this.authorityEnum = authorityEnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorityEnum getAuthorityEnum() {
        return authorityEnum;
    }

    public void setAuthorityEnum(AuthorityEnum authorityEnum) {
        this.authorityEnum = authorityEnum;
    }
}
