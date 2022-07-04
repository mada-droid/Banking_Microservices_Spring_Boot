package com.example.demo.service;

import com.example.demo.dto.AuthorityDTO;
import com.example.demo.dto.AuthorityListDTO;
import com.example.demo.entity.AuthorityList;

import java.util.List;

public interface AuthorityListService {

    void save(AuthorityListDTO authorityListDTO);

    List<AuthorityListDTO> findAuthorityByUserEmail(String email);

    AuthorityListDTO findAuthorityListByUserEmail(String email);
}
