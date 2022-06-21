package com.example.demo.config;

import com.example.demo.dto.AuthorityListDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDTONoPassword;
import com.example.demo.entity.User;
import com.example.demo.service.AuthorityListService;
import com.example.demo.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {


	private static final long serialVersionUID = -2550185165626007488L;

	@Autowired
	private AuthorityListService authorityListService;

	@Autowired
	private UserService userService;

	private String tokenToCheck;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	//	@Value("${jwt.secret}")
	@Value("${jwt.secret}")
	private String secret;

	//retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	//retrieve username from jwt token
	public String getEmailFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public String getIdFromToken(String token){
		return getClaimFromToken(token, Claims::getId);
	}

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
    //for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	//generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		Collection<String> authorities = new ArrayList<>();
		UserDTONoPassword user = userService.findByEmail(userDetails.getUsername());
		int userId = user.getId();

		List<AuthorityListDTO> authorityListDTOS =
				authorityListService.findAuthorityByUserEmail(userDetails.getUsername());

		for (AuthorityListDTO authorityListDTO : authorityListDTOS) {
			System.out.println(authorityListDTO.getAuthorityDTO().getAuthorityEnum());
			authorities.add(authorityListDTO.getAuthorityDTO().getAuthorityEnum().toString());
		}

		claims.put("authorities",authorities);
		claims.put("userId", userId);
		return doGenerateToken(claims, userDetails.getUsername());
	}

	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization
	// (https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	//validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String email = getEmailFromToken(token);
		return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getTokenToCheck() {
		return tokenToCheck;
	}

	public void setTokenToCheck(String tokenToCheck) {
		this.tokenToCheck = tokenToCheck;
	}

	@Override
	public String toString() {
		return "JwtTokenUtil{" +
				"secret='" + secret + '\'' +
				'}';
	}
}