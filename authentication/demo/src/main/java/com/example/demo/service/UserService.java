package com.example.demo.service;

import com.example.demo.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(int theId);

    void save(UserDTO userDTO);

    void deleteById(int theId);

    UserDTO findByEmail(String email);
}
