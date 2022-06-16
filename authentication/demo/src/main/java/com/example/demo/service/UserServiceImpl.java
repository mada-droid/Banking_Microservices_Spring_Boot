package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserDTONoPassword;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTONoPassword> findAll() {

        List<User> userList = userRepository.findAll();
        List<UserDTONoPassword> userDTONoPasswordList = new ArrayList<>();
        for (User user : userList) {
            UserDTONoPassword userDTONoPassword = new UserDTONoPassword(user.getBirthDate(),
                    user.getFirstName(),user.getLastName(),user.getEmail());
            userDTONoPassword.setId(user.getId());
            userDTONoPasswordList.add(userDTONoPassword);
        }
        return userDTONoPasswordList;
    }

    @Override
    public UserDTONoPassword findById(int theId) {
        Optional<User> result = userRepository.findById(theId);

        User user = null;

        if (result.isPresent()) {
            user = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Utente con id: " + theId + " non trovato");
        }

        UserDTONoPassword userDTONoPassword = new UserDTONoPassword(
                user.getBirthDate(), user.getFirstName(),
                user.getLastName(), user.getEmail());
        userDTONoPassword.setId(user.getId());

        return userDTONoPassword;
    }

    @Override
    public void save(UserDTO userDTO) {

        User user = new User(userDTO.getFirstName(), userDTO.getLastName(),
                userDTO.getEmail(), userDTO.getBirthDate(), userDTO.getPassword());

        userRepository.save(user);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public UserDTONoPassword findByEmail(String email) {
        User user =  userRepository.findByEmail(email);

        UserDTONoPassword userDTONoPassword = new UserDTONoPassword(
                user.getBirthDate(), user.getFirstName(), user.getLastName(),
                user.getEmail());

        userDTONoPassword.setId(user.getId());

        return userDTONoPassword;
    }
}
