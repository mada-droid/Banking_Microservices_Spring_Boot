package com.example.demo.dao;

import com.example.demo.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Utente findByUsername(String userName);
}
