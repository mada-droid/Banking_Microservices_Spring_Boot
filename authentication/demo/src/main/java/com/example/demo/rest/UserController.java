package com.example.demo.rest;

import com.example.demo.dto.AuthorityListDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.AuthorityListService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    private AuthorityListService authorityListService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // expose "/utenti" and return list of utenti
    //DA ESSERE VISIBILE SOLO DAL DIPENDENTE **DA FAREEEE**
    @GetMapping("users")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    // add mapping for GET /utenti/{utenteId}
    @GetMapping("/users/{userId}")
    public UserDTO findById(@PathVariable int userId) {
       return userService.findById(userId);
    }

    //add mapping for PUT /utenti - update existing utenti
    @PutMapping("users")
    public UserDTO updateUtente(@RequestBody UserDTO userDTO) {

        userService.save(userDTO);

        return userDTO;
    }

    //add mapping for DELETE /utenti/{utenteId} - delete utente
    @DeleteMapping("users/{userId}")
    public String deleteUtente(@PathVariable int userId) {

        UserDTO tempUtente = userService.findById(userId);

        if (tempUtente == null) {
            throw new RuntimeException("User id- " + userId + "can't be found");
        }

        userService.deleteById(userId);

        return "Deleted user id - " + userId;
    }

    @GetMapping("{userEmail}/authorities")
    public List<AuthorityListDTO> findAuthorityByUserEmail(@PathVariable String userEmail) {
        return authorityListService.findAuthorityByUserEmail(userEmail);
    }

    @GetMapping("prova")
    public String findErrors() {
        return "ProvaMLF";
    }




}
