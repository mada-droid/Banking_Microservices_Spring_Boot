package com.example.demo.service;

import com.example.demo.entity.Utente;

import java.util.List;

public interface UtenteService {

    List<Utente> findAll();

    Utente findById(int theId);

    void save(Utente utente);

    void deleteById(int theId);
}
