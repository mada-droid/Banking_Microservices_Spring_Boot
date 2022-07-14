package com.example.demo.service;


import com.example.demo.dto.ImageDTO;

public interface ImageService {

    void save(ImageDTO imageDTO);

    ImageDTO findImageByUserId(int id);

    Object findByUserId(int id);

    void delete(int id);

}
