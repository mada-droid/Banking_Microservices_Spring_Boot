package com.example.demo.dao;

import com.example.demo.entity.AuthorityList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityListRepository extends JpaRepository<AuthorityList,Integer> {

    List<AuthorityList> findAuthorityByUserEmail(String email);
}
