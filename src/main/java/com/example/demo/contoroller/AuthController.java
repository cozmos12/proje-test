package com.example.demo.contoroller;

import com.example.demo.dto.AuthResponses;
import com.example.demo.entitiy.User;
import com.example.demo.dto.UserRequest;
import com.example.demo.mapper.AuthResponse;
import com.example.demo.security.JwtTokenProvider;

import com.example.demo.service.RefreshTokenService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public AuthResponses login(@RequestBody UserRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),(loginRequest.getPassword()));
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        User user=userService.getOneUserByUserName(loginRequest.getUserName());
        AuthResponses authResponses=new AuthResponses();
        authResponses.setMessage("Bearer" + jwtToken);
        authResponses.setId(user.getId());

        return authResponses;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponses> register(@RequestBody UserRequest registerRequest) {
        AuthResponse authResponse = new AuthResponse();
        AuthResponses authResponses=new AuthResponses();
        if (userService.getOneUserByUserName(registerRequest.getUserName()) != null) {
            authResponse.setMessage("Username already in use.");
            authResponses.setMessage("Username already in use");
            return new ResponseEntity<>(authResponses, HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        authResponses.setMessage("Username already in use");


        userService.save(user);
        return new ResponseEntity<>(authResponses, HttpStatus.CREATED);
    }




}
