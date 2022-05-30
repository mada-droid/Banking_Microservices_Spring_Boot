package com.example.demo.service;

import com.example.demo.dao.UtenteDTO;
import com.example.demo.dao.UtenteRepository;
import com.example.demo.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Utente utente = utenteRepository.findByEmail(email);
		if (utente == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return new User(utente.getEmail(), utente.getPassword(),
				new ArrayList<>());
	}

	public Utente save(UtenteDTO utente) {
		Utente newUtente = new Utente(utente.getNome(),
				utente.getCognome(), utente.getEmail(),
				utente.getDataDiNascita(),/*utente.getUsername()*/
				bcryptEncoder.encode(utente.getPassword()));

		HttpEntity<Utente> request = new HttpEntity<>(new Utente(utente.getNome(),
				utente.getCognome(), utente.getEmail(),
				utente.getDataDiNascita(),/*utente.getUsername()*/
				bcryptEncoder.encode(utente.getPassword())));


		ResponseEntity<Utente> response = restTemplate.exchange("http://localhost:8083/api/utenti", HttpMethod.POST,
				request,Utente.class);


		return utenteRepository.save(newUtente);
	}
}