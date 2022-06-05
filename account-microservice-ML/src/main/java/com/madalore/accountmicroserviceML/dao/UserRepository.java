package com.madalore.accountmicroserviceML.dao;


import com.madalore.accountmicroserviceML.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(int userId);


}
