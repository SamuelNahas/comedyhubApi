package com.comedyhub.prot.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import com.comedyhub.prot.dto.LoginRequest;
import com.comedyhub.prot.dto.TokenResponse;
import com.comedyhub.prot.dto.UserDtoCreate;
import com.comedyhub.prot.dto.UserDtoResponse;
import com.comedyhub.prot.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDtoResponse> registerUser(@RequestBody UserDtoCreate userDto) {
        UserDtoResponse createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    if (!userService.existsByUsername(loginRequest.getUsername())) {
        return ResponseEntity.badRequest().body("User not found");
    }
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(10, ChronoUnit.DAYS))
            .subject(authentication.getName())
            .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new TokenResponse(token));
    } catch (AuthenticationException e) {
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
}

}
