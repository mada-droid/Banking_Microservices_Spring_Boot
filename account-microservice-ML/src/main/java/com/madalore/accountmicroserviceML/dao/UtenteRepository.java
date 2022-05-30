package com.madalore.accountmicroserviceML.dao;


import com.madalore.accountmicroserviceML.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
//    Utente findByUsername(String userName);
}
