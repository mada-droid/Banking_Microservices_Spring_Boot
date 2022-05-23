package com.example.demo.dao;

import com.example.demo.entity.Conto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContoRepository extends JpaRepository<Conto, Integer> {
}
