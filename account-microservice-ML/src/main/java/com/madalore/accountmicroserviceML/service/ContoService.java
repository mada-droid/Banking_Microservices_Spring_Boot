package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dao.ContoDTO;
import com.madalore.accountmicroserviceML.entity.Conto;
import com.madalore.accountmicroserviceML.entity.Utente;

import java.util.List;

public interface ContoService {

//    List<Conto> findAllById(int utenteId);

    List<ContoDTO> findAll();

    List<ContoDTO> findAllByUtenteOrderById(int utenteId);

// List<ContoDTO> findAllById();

    Conto findById(int theId);

    void save(Conto conto);

    void deleteById(int theId);

//    List<Utente> findAllByUtenteIdOrderByUtenteId();

}
