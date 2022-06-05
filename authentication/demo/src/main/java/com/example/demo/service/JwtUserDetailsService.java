package com.example.demo.service;

import com.example.demo.dto.AuthorityListDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Authority;
import com.example.demo.entity.AuthorityEnum;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityListService authorityListService;

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

	public User save(UserDTO userDTO) {
		User newUser = new User(userDTO.getFirstName(),
				userDTO.getLastName(), userDTO.getEmail(),
				userDTO.getBirthDate(),bcryptEncoder.encode(userDTO.getPassword()));

		HttpEntity<User> request = new HttpEntity<>(new User(userDTO.getFirstName(),
				userDTO.getLastName(), userDTO.getEmail(),
				userDTO.getBirthDate(),bcryptEncoder.encode(userDTO.getPassword())));

		ResponseEntity<User> response = restTemplate.exchange("http://localhost:8080/account/users", HttpMethod.POST,
				request, User.class);

		List<Authority> authorityList = new ArrayList<>();
		Authority authority = new Authority(AuthorityEnum.ROLE_CLIENT);
		authorityList.add(authority);
		AuthorityListDTO authorityListDTO = new AuthorityListDTO(authorityList,userDTO);
		authorityListService.save(authorityListDTO);
		return userRepository.save(newUser);

	}
}