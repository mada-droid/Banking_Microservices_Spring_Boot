package com.example.demo.rest;

import com.example.demo.entity.Conto;
import com.example.demo.entity.Utente;
import com.example.demo.service.ContoService;
import com.example.demo.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/utenti")
public class ContoController {

    private ContoService contoService;
    private UtenteService utenteService;

    @Autowired
    public ContoController(ContoService contoService, UtenteService utenteService) {
        this.contoService = contoService;
        this.utenteService = utenteService;
    }

    @GetMapping("/{utenteId}/conti")
    public List<Conto> findAll(@PathVariable int utenteId) {

        Utente utente = utenteService.findById(utenteId);

        if (utente == null) {
            throw new RuntimeException("utente id non trovato - " + utenteId);
        }

        List<Conto> contoList = contoService.findAll();
        List<Conto> contoList1 = new ArrayList<>();

        for (Conto conto : contoList) {
            if(conto.getUtente().getId() == utenteId){
                contoList1.add(conto);
            }
        }

//        return contoService.findAll();
        return contoList1;

    }

    @GetMapping("/conti")
    public List<Conto> findAll() {
        return contoService.findAll();
    }

    @PostMapping("/conti")
    public Conto addConto(@RequestBody Conto conto){
        conto.setNumeroConto(0);
        contoService.save(conto);
        return conto;
    }

    @PutMapping("/conti")
    public Conto updateConto(@RequestBody Conto conto){
        contoService.save(conto);
        return conto;
    }

    @DeleteMapping("/conti/{contoId}")
    public String deleteConto(@PathVariable int contoId){

        Conto tempConto = contoService.findById(contoId);

        if(tempConto == null){
            throw new RuntimeException("id Conto non trovato: " + contoId);
        }

        contoService.deleteById(contoId);

        return "Deleted Conto id - " + contoId;

    }




}
