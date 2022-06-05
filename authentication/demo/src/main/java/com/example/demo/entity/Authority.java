package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private AuthorityEnum authorityEnum;

    public Authority() {
    }

    public Authority(AuthorityEnum authorityEnum) {
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

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authorityEnum=" + authorityEnum +
                '}';
    }
}
