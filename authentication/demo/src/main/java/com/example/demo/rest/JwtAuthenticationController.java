package com.example.demo.rest;

import com.example.demo.jwt.JwtTokenUtil;
import com.example.demo.jwt.JwtRequest;
import com.example.demo.jwt.JwtResponse;
import com.example.demo.dto.*;
import com.example.demo.service.AuthorityListService;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.service.NewAccountMessageSender;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityListService authorityListService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private NewAccountMessageSender newAccountMessageSender;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);

            AuthorityListDTO authorityListDTO = authorityListService.findAuthorityListByUserEmail(userDetails.getUsername());
            UserDTONoPassword userDTONoPassword = userService.findByEmail(userDetails.getUsername());

            return ResponseEntity.ok(new JwtResponse(token,
                    authorityListDTO.getAuthorityDTO().getAuthorityEnum().toString(),
                    userDTONoPassword.getId()));
    }

    @RequestMapping(value = "/userdetails", method = RequestMethod.POST)
    public UserDTONoPassword getUserDetails(@RequestBody JwtRequest authenticationRequest) throws Exception {
        ResponseEntity<?> response = createAuthenticationToken(authenticationRequest);
        if (response != null) {
            return userService.findByEmail(authenticationRequest.getEmail());
        }
        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO user) throws Exception {

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
            AuthorityListDTO authorityListDTO = authorityListService.findAuthorityListByUserEmail(user.getEmail());
            UserDTONoPassword userDTONoPassword = userService.findByEmail(user.getEmail());
            return ResponseEntity.ok(new JwtResponse(token,
                    authorityListDTO.getAuthorityDTO().getAuthorityEnum().toString(),
                    userDTONoPassword.getId()));
        }else{
            throw new RuntimeException("Something went off, please contact support");
        }
    }

    // expose "/utenti" and return list of utenti
    @GetMapping("users")
    public List<UserDTONoPassword> findAll() {
        return userService.findAll();
    }

    // add mapping for GET /utenti/{utenteId}
    @GetMapping("/users/{userId}")
    public UserDTONoPassword findById(@PathVariable int userId) {
        return userService.findById(userId);
    }

    //add mapping for PUT /users - update existing users
    @PutMapping("users/update/email")
    public UserDTONoPassword updateUserEmail(@RequestBody UserUpdateEmailDTO userUpdateEmailDTO) {

        UserDTO userDTO = userService.findUserById(userUpdateEmailDTO.getId());

        List<UserDTONoPassword> userDTONoPasswordList = userService.findAll();

        List<UserDTONoPassword> matches = userDTONoPasswordList.parallelStream()
                .filter((element) -> element.getEmail().equals(userUpdateEmailDTO.getEmail()))
                .collect(Collectors.toList());

        if(userDTO.getEmail().equals(userUpdateEmailDTO.getEmail())){
            throw new RuntimeException("Possiedi già questo email!");
        }
        if(matches.size()>0){
            throw new RuntimeException("Email già esistente, scegline un'altra!");
        }

        userDTO.setEmail(userUpdateEmailDTO.getEmail());
        UserDTONoPassword  userDTONoPassword=  getUserDTONoPasswordEntity(userDTO);
        return userDTONoPassword;
    }

    //add mapping for PUT /users - update existing users
    @PutMapping("users/update/password")
    public UserDTONoPassword updateUserPassword(@RequestBody UserUpdatePasswordDTO userUpdatePasswordDTO) {
        UserDTO userDTO = userService.findUserById(userUpdatePasswordDTO.getId());
        userDTO.setPassword(userUpdatePasswordDTO.getPassword());
        userDTO.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
       UserDTONoPassword  userDTONoPassword=  getUserDTONoPasswordEntity(userDTO);
       return userDTONoPassword;
    }

    private UserDTONoPassword getUserDTONoPasswordEntity(UserDTO userDTO){
        userService.update(userDTO);
        UserDTONoPassword userDTONoPassword = new UserDTONoPassword(userDTO.getBirthDate(),userDTO.getFirstName(),
                userDTO.getLastName(),userDTO.getEmail());
        userDTONoPassword.setId(userDTO.getId());
        return userDTONoPassword;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Credenziali errate!");
        }
    }
}