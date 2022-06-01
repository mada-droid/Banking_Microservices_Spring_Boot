package com.madalore.accountmicroserviceML.rest;

import com.madalore.accountmicroserviceML.dao.ContoDTO;
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
import java.util.Random;

@RestController
@RequestMapping("/api")
public class ContoController {

    @Autowired
    private UtenteService utenteService;

    private String numeroConto = "";

    private ContoService contoService;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    public ContoController(ContoService contoService) {
        this.contoService = contoService;
    }

//    @GetMapping("/{utenteId}/conti")
//    public List<Conto> findAll(@PathVariable int utenteId) {
//
////        List<Conto> contoList = contoService.findAll();
////        List<Conto> contoList1 = new ArrayList<>();
////
////        for (Conto conto : contoList) {
////            if (conto.getUtente().getId() == utenteId) {
////                contoList1.add(conto);
////            }
////        }
////        return contoList1;
//
////        return contoService.findAllById(utenteId);
//
//    }

    @GetMapping("/conti")
    public List<ContoDTO> findAll() {
        return contoService.findAll();
    }

    //    @GetMapping("/conti/dto")
    //    public List<ContoDTO> findAllDTO(){
    //        return contoService.findAllById();
    //    }

    @PostMapping("/conti/{utenteId}/addAccount")
    public Conto addConto(@PathVariable int utenteId) {
//        UtenteController utenteController = new UtenteController(utenteService);

        Utente utente = utenteService.findById(utenteId);
        Conto conto = new Conto("IT" + generaIban()
                , "inattivo", 0.00, utente, getNumeroConto());
        contoService.save(conto);
        return conto;
    }

//    @GetMapping("/newjson")
//    public List<Utente> findAllByUtenteIdOrderByUtenteId(){
//        return contoService.findAllByUtenteIdOrderByUtenteId();
//    }

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
//        UtenteController utenteController = new UtenteController(utenteService);
        Utente utente = utenteService.findById(utenteId);

        List<ContoDTO> contoList = contoService.findAll();
        for (ContoDTO conto : contoList) {
            if(conto.getNumeroConto().equals(numeroConto)){
                if(conto.getUtente().getId() == utenteId){
                    Conto conto1 = new Conto("IT" + generaIban()
                            , "inattivo", 0.00, utente, getNumeroConto());
                    contoService.save(conto1);
                    return conto1;
                }
            }
        }
        throw new RuntimeException("Either your id or account's number is incorrect!");
    }


    //add mapping for POST /utenti - add new utente
    @PostMapping("utenti")
    public Utente addUtente(@RequestBody Utente utente) {

        utente.setId(0);
        Conto conto = new Conto("IT"+generaIban(),"inattivo", 0.00, utente,numeroConto);
//        Authority authority = new Authority("ROLE_CLIENTE", utente);
        utenteService.save(utente);
        contoService.save(conto);
//        authorityService.save(authority);
        return utente;
    }

    public String generaIban(){
        numeroConto = "";
        String alfa = "QWERTYUIOPLKJHGFDSAZXCVBNM";
        String iban = "";
        Random random = new Random();

        for(int i = 2; i < 27; i++){
            if(i == 4){
                iban +=  alfa.charAt(random.nextInt(25));
                continue;
            }
            int randomNumber = random.nextInt(1,9);
            if(i >= 15){
                numeroConto += String.valueOf(randomNumber);
            }
            iban += String.valueOf(randomNumber);
        }

        return iban;
    }

    public String getNumeroConto() {
        return numeroConto;
    }

    public void setNumeroConto(String numeroConto) {
        this.numeroConto = numeroConto;
    }



}
