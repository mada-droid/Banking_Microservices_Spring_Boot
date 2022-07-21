package com.example.demo.dao;

import com.example.demo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image findImageByUserId(int id);

    Image findByUserId(int id);

    void deleteById(int id);
}
