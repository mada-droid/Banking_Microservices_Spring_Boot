package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotNull(message = "Il campo nome non può essere null !")
    @Size(min = 1, max = 64, message
            = "Il nome deve avere come minimo 1 carattere e non può superare i 64 caratteri")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Il campo cognome non può essere null !")
    @Size(min = 1, max = 64, message
            = "Il cognome deve avere come minimo 1 carattere e non può superare i 64 caratteri")
    private String lastName;

    @Column(name = "email")
    @NotNull(message = "Il campo email non può essere null !")
    @Size(min = 1, max = 64, message
            = "l'email deve avere come minimo 1 carattere e non può superare i 64 caratteri")
    @Email(message = "email non è valido ")
    private String email;

    @Column(name = "birth_date")
    @NotNull(message = "Il campo data di nascita non può essere null !")
    private Date birthDate;

    @Column(name = "password")
    @NotNull(message = "Il campo password non può essere vuoto !")
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String email, Date birthDate, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
    }

    public User(int id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
