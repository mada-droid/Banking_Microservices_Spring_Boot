package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dao.ContoDTO;
import com.madalore.accountmicroserviceML.dao.ContoRepository;
import com.madalore.accountmicroserviceML.entity.Conto;
import com.madalore.accountmicroserviceML.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ContoServiceImpl implements ContoService{

    private ContoRepository contoRepository;

    @Autowired
    public ContoServiceImpl(ContoRepository contoRepository) {
        this.contoRepository = contoRepository;
    }

//    @Override
//    public List<Conto> findAllById(int utenteId) {
//        return contoRepository.findAllByUtenteOrderById(utenteId);
//    }

    @Override
    public List<ContoDTO> findAll() {

        return contoRepository
                .findAll()
                .stream()
                .map(x->new ContoDTO(x.getNumeroConto(),x.getIban(),
                x.getStato(),x.getSaldo(),x.getUtente())).collect(Collectors.toList());
    }

    @Override
    public List<ContoDTO> findAllByUtenteOrderById(int utenteId) {
        return contoRepository.findAllByUtenteOrderById(utenteId).stream().map(x->new ContoDTO(x.getNumeroConto(),x.getIban(),
                x.getStato(),x.getSaldo(),x.getUtente())).collect(Collectors.toList());
    }

//    @Override
//    public List<ContoDTO> findAllById() {
//        return contoRepository.findAllById();
//    }

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

//    @Override
//    public List<Utente> findAllByUtenteIdOrderByUtenteId() {
//        return contoRepository.findAllByUtenteIdOrderByUtenteId();
//    }


}
