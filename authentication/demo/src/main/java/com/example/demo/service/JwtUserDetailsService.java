package com.example.demo.service;

import com.example.demo.dao.UtenteDTO;
import com.example.demo.dao.UtenteRepository;
import com.example.demo.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente utente = utenteRepository.findByUsername(username);
		if (utente == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(utente.getUsername(), utente.getPassword(),
				new ArrayList<>());
	}
	
	public Utente save(UtenteDTO utente) {
		Utente newUtente = new Utente(utente.getNome(),
				utente.getCognome(), utente.getEmail(),
				utente.getDataDiNascita(),utente.getUsername()
				,bcryptEncoder.encode(utente.getPassword()));
//		newUtente.setUsername(utente.getUsername());
//		newUtente.setPassword(bcryptEncoder.encode(utente.getPassword()));
		return utenteRepository.save(newUtente);
	}
}