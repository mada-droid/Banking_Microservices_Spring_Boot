package com.example.demo.rest;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.dao.JwtRequest;
import com.example.demo.dao.JwtResponse;
import com.example.demo.dto.NewAccountMessageDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.service.NewAccountMessageSender;
import com.example.demo.service.UserService;
import com.rabbitmq.client.AMQP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
//@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private NewAccountMessageSender newAccountMessageSender;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/userdetails", method = RequestMethod.POST)
    public UserDTO getUserDetails(@RequestBody JwtRequest authenticationRequest) throws Exception {
        ResponseEntity<?> response = createAuthenticationToken(authenticationRequest);
        if (response != null) {
            return userService.findByEmail(authenticationRequest.getEmail());
        }
        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {

        userDetailsService.save(user);

        NewAccountMessageDTO newAccountMessageDTO = new NewAccountMessageDTO(user.getId());

        if (newAccountMessageSender.sendAccountMessage(newAccountMessageDTO)) {
            final String token = jwtTokenUtil.generateToken(new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public String getUsername() {
                    return user.getEmail();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            });
            return ResponseEntity.ok(new JwtResponse(token));
        }else{
            throw new RuntimeException("Something went off, please contact support");
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}