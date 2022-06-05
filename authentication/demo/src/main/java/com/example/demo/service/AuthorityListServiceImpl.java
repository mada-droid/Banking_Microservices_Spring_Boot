package com.example.demo.service;

import com.example.demo.dao.AuthorityListRepository;
import com.example.demo.dto.AuthorityListDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.AuthorityList;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorityListServiceImpl implements AuthorityListService {

    private AuthorityListRepository authorityListRepository;


    @Autowired
    public AuthorityListServiceImpl(AuthorityListRepository authorityListRepository) {
        this.authorityListRepository = authorityListRepository;
    }

    @Override
    public void save(AuthorityListDTO authorityListDTO) {
        User user = new User(authorityListDTO.getUserDTO().getFirstName(),authorityListDTO.getUserDTO().getLastName(),
                authorityListDTO.getUserDTO().getEmail(),authorityListDTO.getUserDTO().getBirthDate(),
                authorityListDTO.getUserDTO().getPassword());
        AuthorityList authorityList = new AuthorityList(authorityListDTO.getAuthority(),user);
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
            UserDTO userDTO = new UserDTO(list.getUser().getPassword(),list.getUser().getBirthDate()
                    ,list.getUser().getFirstName(),
                    list.getUser().getLastName(), list.getUser().getEmail());
            AuthorityListDTO authorityListDTO = new AuthorityListDTO(list.getAuthority(),userDTO);
            authorityListDTOS.add(authorityListDTO);
        }
        return authorityListDTOS;

    }
}
