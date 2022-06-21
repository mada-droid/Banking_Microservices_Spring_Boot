package com.example.demo.rest;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.dto.*;
import com.example.demo.service.AuthorityListService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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

    //add mapping for PUT /users - update existing users
    @PutMapping("users/update/email")
    public UserDTONoPassword updateUtenteEmail(@RequestBody UserUpdateEmailDTO userUpdateEmailDTO) {

        UserDTO userDTO = userService.findUserById(userUpdateEmailDTO.getId());
        userDTO.setEmail(userUpdateEmailDTO.getEmail());
        userService.update(userDTO);
        UserDTONoPassword userDTONoPassword = new UserDTONoPassword(userDTO.getBirthDate(),userDTO.getFirstName(),
                userDTO.getLastName(),userDTO.getEmail());
        userDTONoPassword.setId(userDTO.getId());
            return userDTONoPassword;
    }


    //add mapping for PUT /users - update existing users
    @PutMapping("users/update/password")
    public UserDTONoPassword updateUtentePassword(@RequestBody UserUpdatePasswordDTO userUpdatePasswordDTO) {

        UserDTO userDTO = userService.findUserById(userUpdatePasswordDTO.getId());
        userDTO.setPassword(userUpdatePasswordDTO.getPassword());
        userDTO.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        userService.update(userDTO);
        UserDTONoPassword userDTONoPassword = new UserDTONoPassword(userDTO.getBirthDate(),userDTO.getFirstName(),
                userDTO.getLastName(),userDTO.getEmail());
        userDTONoPassword.setId(userDTO.getId());
        return userDTONoPassword;
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
