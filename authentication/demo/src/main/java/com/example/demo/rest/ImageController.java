package com.example.demo.rest;

import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.ImageService;
import com.example.demo.util.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:8082") open for specific port
//@CrossOrigin() // open for all ports
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file, UserDTO userDTO)
            throws IOException {

        ImageDTO imageDTO = new ImageDTO(file.getOriginalFilename(), ImageUtility.compressImage(file.getBytes()), userDTO);

        imageService.save(imageDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/image/info/{userId}"})
    public ImageDTO getImageDetails(@PathVariable int userId) throws IOException {

        return imageService.findImageByUserId(userId);
    }

    @GetMapping(path = {"/get/image/{userId}"})
    public ResponseEntity<byte[]> getImage(@PathVariable int userId) throws IOException {

        ImageDTO dbImage = imageService.findImageByUserId(userId);

        return ResponseEntity
                .ok()
                .body(ImageUtility.decompressImage(dbImage.getImage()));
    }
}