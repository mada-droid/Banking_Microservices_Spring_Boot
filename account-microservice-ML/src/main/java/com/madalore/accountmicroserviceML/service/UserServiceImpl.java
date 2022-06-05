package com.madalore.accountmicroserviceML.service;

import com.madalore.accountmicroserviceML.dto.UserDTO;
import com.madalore.accountmicroserviceML.dao.UserRepository;
import com.madalore.accountmicroserviceML.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {

        return userRepository.findAll().stream().map(x-> new UserDTO(x.getBirthDate()
                ,x.getFirstName(),x.getLastName(),x.getEmail(), x.getPassword())).collect(Collectors.toList());
    }

   public UserDTO findUserById(int userId){
        User user = userRepository.findUserById(userId);
        UserDTO userDTO = new UserDTO(user.getBirthDate(),
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return userDTO;
   }

    @Override
    public void save(UserDTO userDTO) {

        User user = new User(userDTO.getFirstName(), userDTO.getLastName(),
                userDTO.getEmail(), userDTO.getBirthDate(), userDTO.getPassword());

        user.setId(0);

        userRepository.save(user);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }
}
