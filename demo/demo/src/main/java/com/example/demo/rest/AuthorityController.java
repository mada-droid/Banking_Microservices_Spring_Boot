package com.example.demo.rest;

import com.example.demo.entity.Authority;
import com.example.demo.entity.Conto;
import com.example.demo.entity.Utente;
import com.example.demo.service.AuthorityService;
import com.example.demo.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class AuthorityController {

    private AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping("authorities")
    public List<Authority> findAll() {
        return authorityService.findAll();
    }


    // add mapping for GET /utenti/{utenteId}
    @GetMapping("/authorities/{authorityId}")
    public Authority getAuthority(@PathVariable int authorityId) {

        Authority authority = authorityService.findById(authorityId);

        if (authority == null) {
            throw new RuntimeException("utente id non trovato - " + authorityId);
        }

        return authority;
    }

    @PostMapping("/authorities")
    public Authority addAuthority(@RequestBody Authority authority){
        authority.setId(0);
        authorityService.save(authority);
        return authority;
    }
}
