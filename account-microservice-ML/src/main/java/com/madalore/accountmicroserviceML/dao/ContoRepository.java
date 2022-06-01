package com.madalore.accountmicroserviceML.dao;

import com.madalore.accountmicroserviceML.entity.Conto;
import com.madalore.accountmicroserviceML.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContoRepository extends JpaRepository<Conto, Integer> {
    List<Conto> findAllByUtenteOrderById(int utenteId);

    List<Conto> findAllByUtenteFirstNameAndStato(String firstName, String stato);

    //    List<Utente> findAllByUtenteIdOrderByUtenteId();
//    List<Conto> findAll();
}
