package com.example.demo.service;

import com.example.demo.dto.AuthorityListDTO;
import com.example.demo.entity.AuthorityList;

import java.util.List;

public interface AuthorityListService {


    void save(AuthorityListDTO authorityListDTO);

    void deleteById(int theId);

    List<AuthorityListDTO> findAuthorityByUserEmail(String email);
}
