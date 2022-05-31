package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.entity.Utente;

import java.util.List;

public interface UtenteService {

    List<Utente> findAll();

    Utente findById(int theId);

    void save(Utente utente);

    void deleteById(int theId);
}
