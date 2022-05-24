package com.example.demo.service;

import com.example.demo.dao.ContoRepository;
import com.example.demo.entity.Conto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContoServiceImpl  implements  ContoService{


    private ContoRepository contoRepository;

    @Autowired
    public ContoServiceImpl(ContoRepository contoRepository) {
        this.contoRepository = contoRepository;
    }

    @Override
    public List<Conto> findAll() {
        return contoRepository.findAll();
    }

    @Override
    public Conto findById(int theId) {
        Optional<Conto> result = contoRepository.findById(theId);

        Conto conto = null;

        if (result.isPresent()) {
            conto = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Conto con id: " + theId + " non trovato");
        }

        return conto;
    }

    @Override
    public void save(Conto conto) {
        contoRepository.save(conto);
    }

    @Override
    public void deleteById(int theId) {
        contoRepository.deleteById(theId);
    }
}
