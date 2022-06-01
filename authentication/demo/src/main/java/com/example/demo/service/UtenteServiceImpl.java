package com.example.demo.service;

import com.example.demo.dao.UtenteRepository;
import com.example.demo.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UtenteServiceImpl implements UtenteService{

    private UtenteRepository utenteRepository;

    @Autowired
    public UtenteServiceImpl(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    @Override
    public Utente findById(int theId) {
        Optional<Utente> result = utenteRepository.findById(theId);

        Utente utente = null;

        if (result.isPresent()) {
            utente = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Utente con id: " + theId + " non trovato");
        }

        return utente;
    }

    @Override
    public void save(Utente utente) {
        utenteRepository.save(utente);
    }

    @Override
    public void deleteById(int theId) {
        utenteRepository.deleteById(theId);
    }

    @Override
    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }
}
