package com.example.demo.dao;

import com.example.demo.entity.Authority;
import com.example.demo.entity.AuthorityList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityListRepository extends JpaRepository<AuthorityList,Integer> {

    List<AuthorityList> findAuthorityByUserEmail(String email);
}
