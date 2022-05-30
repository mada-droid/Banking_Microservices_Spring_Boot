package com.madalore.accountmicroserviceML.rest;

import com.madalore.accountmicroserviceML.entity.Conto;
import com.madalore.accountmicroserviceML.entity.Utente;
import com.madalore.accountmicroserviceML.service.ContoService;
import com.madalore.accountmicroserviceML.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContoController {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    private ContoService contoService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public ContoController(ContoService contoService) {
        this.contoService = contoService;
    }

    @GetMapping("/{utenteId}/conti")
    public List<Conto> findAll(@PathVariable int utenteId) {

        List<Conto> contoList = contoService.findAll();
        List<Conto> contoList1 = new ArrayList<>();

        for (Conto conto : contoList) {
            if (conto.getUtente().getId() == utenteId) {
                contoList1.add(conto);
            }
        }
        return contoList1;
    }

    @GetMapping("/conti")
    public List<Conto> findAll() {
        return contoService.findAll();
    }

    @PostMapping("/conti/{utenteId}/addAccount")
    public Conto addConto(@PathVariable int utenteId) {
        UtenteController utenteController = new UtenteController(utenteService);

        Utente utente = utenteService.findById(utenteId);
        Conto conto = new Conto("IT" + utenteController.generaIban()
                , "inattivo", 0.00, utente, utenteController.getNumeroConto());
        contoService.save(conto);
        return conto;
    }

    @PutMapping("/conti")
    public Conto updateConto(@RequestBody Conto conto) {
        contoService.save(conto);
        return conto;
    }

    @DeleteMapping("/conti/{contoId}")
    public String deleteConto(@PathVariable int contoId) {

        Conto tempConto = contoService.findById(contoId);

        if (tempConto == null) {
            throw new RuntimeException("id Conto non trovato: " + contoId);
        }

        contoService.deleteById(contoId);

        return "Deleted Conto id - " + contoId;

    }

    @PutMapping("conti/attivazione/{theId}")
    public Conto attivareConto(@PathVariable int theId) {
        Conto conto = contoService.findById(theId);
        conto.setStato("attivo");
        contoService.save(conto);
        return conto;

    }

    @RequestMapping(value = "/conti/{utenteId}/addAccount/{numeroConto}",method = RequestMethod.POST)
    public Conto addAccounts(@PathVariable("utenteId") int utenteId,@PathVariable("numeroConto") String numeroConto){
        UtenteController utenteController = new UtenteController(utenteService);
        Utente utente = utenteService.findById(utenteId);

        List<Conto> contoList = contoService.findAll();
        for (Conto conto : contoList) {
            if(conto.getNumeroConto().equals(numeroConto)){
                if(conto.getUtente().getId() == utenteId){
                    Conto conto1 = new Conto("IT" + utenteController.generaIban()
                            , "inattivo", 0.00, utente, utenteController.getNumeroConto());
                    contoService.save(conto1);
                    return conto1;
                }
            }
        }
        throw new RuntimeException("Either your id or account's number is incorrect!");
    }



}
