package com.example.springcloud.gateway.apiGateway.repo;

import com.example.springcloud.gateway.apiGateway.filter.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(int theId);

    void save(UserDTO userDTO);

    void deleteById(int theId);

    UserDTO findByEmail(String email);
}
