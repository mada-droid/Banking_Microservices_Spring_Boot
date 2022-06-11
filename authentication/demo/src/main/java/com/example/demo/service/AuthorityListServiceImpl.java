package com.example.demo.service;

import com.example.demo.dao.AuthorityListRepository;
import com.example.demo.dao.AuthorityRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.dto.AuthorityListDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.AuthorityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityListServiceImpl implements AuthorityListService {

    private AuthorityListRepository authorityListRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthorityListServiceImpl(AuthorityListRepository authorityListRepository) {
        this.authorityListRepository = authorityListRepository;
    }


    @Override
    public void save(AuthorityListDTO authorityListDTO) {

        AuthorityList authorityList = new AuthorityList(authorityRepository.findById(1).get(),
                userRepository.findById(authorityListDTO.getUserDTO().getId()).get());

        authorityListRepository.save(authorityList);
    }

    @Override
    public void deleteById(int theId) {
        authorityListRepository.deleteById(theId);
    }

    @Override
    public List<AuthorityListDTO> findAuthorityByUserEmail(String email) {

        List<AuthorityList> authorityList = authorityListRepository.findAuthorityByUserEmail(email);
        List<AuthorityListDTO> authorityListDTOS = new ArrayList<>();

        for (AuthorityList list : authorityList) {
            UserDTO userDTO = new UserDTO(list.getUser().getPassword(), list.getUser().getBirthDate()
                    , list.getUser().getFirstName(),
                    list.getUser().getLastName(), list.getUser().getEmail());
            AuthorityDTO authorityDTO = new AuthorityDTO(list.getAuthority().getAuthorityEnum());
            AuthorityListDTO authorityListDTO = new AuthorityListDTO(authorityDTO, userDTO);
            authorityListDTOS.add(authorityListDTO);
        }
        return authorityListDTOS;

    }
}
