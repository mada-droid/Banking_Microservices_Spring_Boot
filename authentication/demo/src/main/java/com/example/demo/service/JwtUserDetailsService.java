package com.example.demo.service;

import com.example.demo.dao.AuthorityListRepository;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.dto.AuthorityListDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.AuthorityEnum;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityListService authorityListService;

    @Autowired
    private AuthorityListRepository authorityListRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    public UserDetails loadUserByUserId(int theId) throws UsernameNotFoundException {
        User user = userRepository.findUserById(theId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with id: " + theId);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }


    public int save(UserDTO userDTO) {

        User newUser = new User(userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getEmail(),
                userDTO.getBirthDate(), bcryptEncoder.encode(userDTO.getPassword()));
        newUser = userRepository.save(newUser);

        AuthorityDTO authorityDTO = new AuthorityDTO(AuthorityEnum.ROLE_CLIENT);
        userDTO.setId(newUser.getId());

        AuthorityListDTO authorityListDTO = new AuthorityListDTO(authorityDTO, userDTO);

        authorityListService.save(authorityListDTO);

        System.out.println( "Authority -> " + authorityListDTO.getAuthorityDTO().getAuthorityEnum());

        return newUser.getId();

    }
}