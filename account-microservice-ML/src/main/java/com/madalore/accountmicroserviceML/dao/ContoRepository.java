package com.madalore.accountmicroserviceML.dao;

import com.madalore.accountmicroserviceML.entity.Conto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContoRepository extends JpaRepository<Conto, Integer> {
}
