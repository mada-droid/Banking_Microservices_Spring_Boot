package com.example.transactionmicroservice.transactionmicroservice.dao;

import com.example.transactionmicroservice.transactionmicroservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
