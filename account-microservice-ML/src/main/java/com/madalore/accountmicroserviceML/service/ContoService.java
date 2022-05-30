package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.entity.Conto;

import java.util.List;

public interface ContoService {

    List<Conto> findAll();

    Conto findById(int theId);

    void save(Conto conto);

    void deleteById(int theId);

}
