package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private String birthDate;

//    @Column(name = "username")
//    private String username;

    @Column(name = "password")
//    @JsonIgnore ** CHIESTA DEL FE SE LO POTRANNO NASCONDERE LORO **
    private String password;

    public Utente() {
    }

    public Utente(String firstName, String lastName, String email, String birthDate,/* String username,*/ String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
//        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String nome) {
        this.firstName = nome;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String cognome) {
        this.lastName = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String dataDiNascita) {
        this.birthDate = dataDiNascita;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + firstName + '\'' +
                ", cognome='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dataDiNascita='" + birthDate + '\'' +
//                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
