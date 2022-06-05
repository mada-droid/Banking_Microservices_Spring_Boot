package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findUserById(int theId);

    void save(UserDTO userDTO);

    void deleteById(int theId);
}
