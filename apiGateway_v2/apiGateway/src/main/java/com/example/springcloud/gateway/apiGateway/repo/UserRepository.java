package com.example.springcloud.gateway.apiGateway.repo;

import com.example.springcloud.gateway.apiGateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

}
