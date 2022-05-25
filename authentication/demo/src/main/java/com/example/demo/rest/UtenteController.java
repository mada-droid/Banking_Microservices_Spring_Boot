package com.example.demo.rest;

import com.example.demo.entity.Authority;
import com.example.demo.entity.Conto;
import com.example.demo.entity.Utente;
import com.example.demo.service.AuthorityService;
import com.example.demo.service.ContoService;
import com.example.demo.service.UtenteService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UtenteController {

    private UtenteService utenteService;

    @Autowired
    private ContoService contoService;

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
        Conto conto = new Conto("inattivo", 0.00, utente);
        Authority authority = new Authority("ROLE_CLIENTE", utente);
        utenteService.save(utente);
        contoService.save(conto);
        authorityService.save(authority);
        return utente;
    }

//    //add mapping for POST /utenti - add new utente
//    @PostMapping("login")
//    public String logUtente(@RequestHeader String username, @RequestHeader String password) {
//
//        Boolean passwordCoincide = false;
//        List<Utente> utenteList = utenteService.findAll();
//        for (Utente utente1 : utenteList) {
//            if (utente1.getUsername() == username) {
//                if (password == utente1.getPassword()) {
//                    passwordCoincide = true;
//                    break;
//                }
//            }
//        }
//        if (passwordCoincide) {
//            String jws = Jwts.builder()
//                    .setIssuer("Stormpath")
//                    .setSubject("msilverman")
//                    .claim("name", "Micah Silverman")
//                    .claim("scope", "admins")
//                    // Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
//                    .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
//                    // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
//                    .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
//                    .signWith(
//                            SignatureAlgorithm.HS256,
//                            TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
//                    )
//                    .compact();
//            return "si la password coincide con la username del utente";
//
//        } else {
//            return "username o/e password errato !";
//        }
//    }

    @RequestMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        //create model attribute to bind form data
        Utente utente = new Utente();

        model.addAttribute("utente", utente);

        return "customer-form";//DOVREMMO PASSARE LA PAGINA HTML APPOSTA
    }

    @PostMapping("/saveUtente")
    public String saveUtente(@ModelAttribute("utente") Utente utente) {

        utenteService.save(utente);
        //TORNARE ALLA LOGIN PAGE CON UNA NOTIFICA CHE REGISTRAZIONE AVVENUTA
        return "redirect:/customer/list";
    }


    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {

        // return "plain-login";

        return "fancy-login";

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
