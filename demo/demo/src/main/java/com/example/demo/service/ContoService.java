package com.example.demo.service;

import com.example.demo.entity.Conto;

import java.util.List;

public interface ContoService {

     List<Conto> findAll();

     Conto findById(int theId);

     void save(Conto conto);

     void deleteById(int theId);

}
