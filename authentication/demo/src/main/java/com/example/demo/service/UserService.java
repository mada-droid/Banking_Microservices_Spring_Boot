package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDTONoPassword;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTONoPassword> findAll();

    UserDTONoPassword findById(int theId);

    void save(UserDTO userDTO);

    void update(UserDTO userDTO);

    UserDTO findUserById(int theId);

    void deleteById(int theId);

    UserDTONoPassword findByEmail(String email);
}
