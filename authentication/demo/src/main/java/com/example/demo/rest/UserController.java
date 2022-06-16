package com.example.demo.rest;

import com.example.demo.dto.AuthorityListDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDTONoPassword;
import com.example.demo.service.AuthorityListService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping("/api/auth")
public class UserController {

    private UserService userService;

    @Autowired
    private AuthorityListService authorityListService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // expose "/utenti" and return list of utenti
    @GetMapping("users")
    public List<UserDTONoPassword> findAll() {
        return userService.findAll();
    }

    // add mapping for GET /utenti/{utenteId}
    @GetMapping("/users/{userId}")
    public UserDTONoPassword findById(@PathVariable int userId) {
       return userService.findById(userId);
    }

    //add mapping for PUT /utenti - update existing utenti
    @PutMapping("users/update")
    public UserDTO updateUtente(@RequestBody UserDTO userDTO) {

        userService.save(userDTO);

        return userDTO;
    }

    //add mapping for DELETE /utenti/{utenteId} - delete utente
    @DeleteMapping("users/{userId}/delete")
    public String deleteUtente(@PathVariable int userId) {

        UserDTONoPassword tempUtente = userService.findById(userId);

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



}
