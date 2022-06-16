package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDTONoPassword;

import java.util.List;

public interface UserService {

    List<UserDTONoPassword> findAll();

    UserDTONoPassword findById(int theId);

    void save(UserDTO userDTO);

    void deleteById(int theId);

    UserDTONoPassword findByEmail(String email);
}
