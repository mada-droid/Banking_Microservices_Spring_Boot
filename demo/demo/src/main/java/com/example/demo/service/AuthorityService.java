package com.example.demo.service;

import com.example.demo.entity.Authority;
import com.example.demo.entity.Conto;

import java.util.List;

public interface AuthorityService {

    List<Authority> findAll();

    Authority findById(int theId);

    void save(Authority authority);

    void deleteById(int theId);
}
