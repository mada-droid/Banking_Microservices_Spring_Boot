package com.example.demo.rest;

import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDTONoPassword;
import com.example.demo.dto.UserImageDTO;
import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;
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

    @Autowired
    UserService userService;


    @PostMapping("/upload/image/{userId}")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable int userId)
            throws IOException {

        UserDTO userDTO = userService.findUserById(userId);

        if(!(imageService.findByUserId(userId).equals("1"))) {
            ImageDTO imageDTO = (ImageDTO) imageService.findByUserId(userId);
            imageService.delete(imageDTO.getId());
        }

            ImageDTO imageDTO = new ImageDTO(file.getOriginalFilename(),
                    ImageUtility.compressImage(file.getBytes()), userDTO,
                    file.getContentType());

            imageService.save(imageDTO);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ImageUploadResponse("Image uploaded successfully: " +
                            file.getOriginalFilename()));

    }

    @GetMapping(path = {"/get/image/info/{userId}"})
    public ImageDTO getImageDetails(@PathVariable int userId) throws IOException {

        ImageDTO imageDTO = imageService.findImageByUserId(userId);
        imageDTO.setImage(ImageUtility.decompressImage(imageDTO.getImage()));
        return imageDTO;
    }

    @GetMapping(path = {"/get/image/{userId}"})
    public ResponseEntity<byte[]> getImage(@PathVariable int userId) throws IOException {

        ImageDTO dbImage = imageService.findImageByUserId(userId);

        if(dbImage != null){
            return ResponseEntity
                    .ok()
                    .body(ImageUtility.decompressImage(dbImage.getImage()));
        }

        throw new RuntimeException("Questo user non ha una foto !");


    }
}