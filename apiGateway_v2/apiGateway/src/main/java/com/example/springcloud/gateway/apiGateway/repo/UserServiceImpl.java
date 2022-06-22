package com.example.springcloud.gateway.apiGateway.repo;

import com.example.springcloud.gateway.apiGateway.entity.User;
import com.example.springcloud.gateway.apiGateway.filter.UserDTO;
import com.example.springcloud.gateway.apiGateway.repo.UserRepository;
import com.example.springcloud.gateway.apiGateway.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<UserDTO> findAll() {

        return userRepository.findAll().stream()
                .map(x -> new UserDTO(x.getPassword(),
                        x.getBirthDate(),x.getFirstName(),
                        x.getLastName(),x.getEmail())).collect(Collectors.toList());

    }

    @Override
    public UserDTO findById(int theId) {
        Optional<User> result = userRepository.findById(theId);

        User user = null;

        if (result.isPresent()) {
            user = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Utente con id: " + theId + " non trovato");
        }

        UserDTO userDTO = new UserDTO(user.getPassword(),
                user.getBirthDate(), user.getFirstName(),
                user.getLastName(), user.getEmail());

        return userDTO;
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
    public UserDTO findByEmail(String email) {
        User user =  userRepository.findByEmail(email);

        UserDTO userDTO = new UserDTO(user.getPassword(),
                user.getBirthDate(), user.getFirstName(), user.getLastName(),
                user.getEmail());

        userDTO.setId(user.getId());

        return userDTO;
    }
}
