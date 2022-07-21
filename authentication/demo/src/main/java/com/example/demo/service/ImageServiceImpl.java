package com.example.demo.service;

import com.example.demo.dao.ImageRepository;
import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDTONoPassword;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void save(ImageDTO imageDTO) {

        User user = new User(imageDTO.getUserDTO().getFirstName(),
                imageDTO.getUserDTO().getLastName(),imageDTO.getUserDTO().getEmail(),
                imageDTO.getUserDTO().getBirthDate(),imageDTO.getUserDTO().getPassword());
        user.setId(imageDTO.getUserDTO().getId());

        Image image = new Image(imageDTO.getName(),imageDTO.getImage(),user,imageDTO.getType());
        image.setId(0);

        imageRepository.save(image);
    }

    @Override
    public ImageDTO findImageByUserId(int id) {

        Image image = imageRepository.findImageByUserId(id);
        UserDTO userDTO = new UserDTO(image.getUser().getPassword(),image.getUser().getBirthDate(),
                image.getUser().getFirstName(),image.getUser().getLastName(),image.getUser().getEmail());
        userDTO.setId(image.getUser().getId());
        ImageDTO imageDTO = new ImageDTO(image.getName(),image.getImage(),userDTO,image.getType());
        imageDTO.setId(image.getId());
        return imageDTO;
    }

    @Override
    public Object findByUserId(int id) {

        Optional<Image> result = Optional.ofNullable(imageRepository.findByUserId(id));

        Image image = null;

        if (result.isPresent()) {
            image = result.get();

            UserDTO userDTO = new UserDTO(image.getUser().getPassword(), image.getUser().getBirthDate()
                    , image.getUser().getFirstName(), image.getUser().getLastName(), image.getUser().getEmail());

            ImageDTO imageDTO = new ImageDTO(image.getName(), image.getImage(), userDTO, image.getType());
            imageDTO.setId(image.getId());

            return imageDTO;
        }

       return "1";

    }

    @Override
    public void delete(int id) {

        imageRepository.deleteById(id);
    }

}
