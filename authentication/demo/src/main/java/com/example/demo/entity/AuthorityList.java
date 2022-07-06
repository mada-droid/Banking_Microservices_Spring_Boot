package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "auth_list")
public class AuthorityList {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "auth_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Authority authority;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public AuthorityList() {
    }

    public AuthorityList(Authority authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority=" + authority +
                ", user=" + user +
                '}';
    }
}
