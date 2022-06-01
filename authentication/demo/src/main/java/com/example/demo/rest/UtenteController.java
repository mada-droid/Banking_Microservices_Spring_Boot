package com.example.demo.rest;

import com.example.demo.entity.Authority;
import com.example.demo.entity.Utente;
import com.example.demo.service.AuthorityService;
import com.example.demo.service.UtenteService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping("/api")
public class UtenteController {

    private UtenteService utenteService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;

    }

    // expose "/utenti" and return list of utenti

    //DA ESSERE VISIBILE SOLO DAL DIPENDENTE **DA FAREEEE**

    public List<Utente> findAll() {
        return utenteService.findAll();
    }

    @GetMapping("/utenti")//DA RIVEDERE CON KLAJD PER LA PARTE DELLA FILTRAZIONE DINAMICA
    public MappingJacksonValue findAllSenzaPassword() {
        List<Utente> utenteList = findAll();

        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter
                        .filterOutAllExcept("username", "password");

        FilterProvider filters =
                new SimpleFilterProvider().addFilter("password", filter);


        MappingJacksonValue mapping = new MappingJacksonValue(utenteList);
        mapping.setFilters(filters);

        return mapping;
    }

    // add mapping for GET /utenti/{utenteId}
    @GetMapping("/utenti/{utenteId}")
    public Utente getUtente(@PathVariable int utenteId) {

        Utente utente = utenteService.findById(utenteId);

        if (utente == null) {
            throw new RuntimeException("utente id non trovato - " + utenteId);
        }

        return utente;
    }


    //add mapping for POST /utenti - add new utente
    @PostMapping("utenti")
    public Utente addUtente(@RequestBody Utente utente) {

        utente.setId(0);
        Authority authority = new Authority("ROLE_CLIENTE", utente);
        utenteService.save(utente);
        authorityService.save(authority);
        return utente;
    }

    //add mapping for PUT /utenti - update existing utenti
    @PutMapping("utenti")
    public Utente updateUtente(@RequestBody Utente utente) {

        utenteService.save(utente);

        return utente;
    }

    //add mapping for DELETE /utenti/{utenteId} - delete utente
    @DeleteMapping("utenti/{utenteId}")
    public String deleteUtente(@PathVariable int utenteId) {

        Utente tempUtente = utenteService.findById(utenteId);

        if (tempUtente == null) {
            throw new RuntimeException("Utente id non trovato - " + utenteId);
        }

        utenteService.deleteById(utenteId);

        return "Deleted utente id - " + utenteId;
    }


}
