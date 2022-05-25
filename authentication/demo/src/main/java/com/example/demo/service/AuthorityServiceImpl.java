package com.example.demo.service;

import com.example.demo.dao.AuthorityRepository;
import com.example.demo.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    private AuthorityRepository authorityRepository;


    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findById(int theId) {
        Optional<Authority> result = authorityRepository.findById(theId);

        Authority authority= null;

        if (result.isPresent()) {
            authority = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Ruolo con id:  " + theId + " non trovato");
        }

        return authority;
    }

    @Override
    public void save(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    public void deleteById(int theId) {
        authorityRepository.deleteById(theId);
    }
}
