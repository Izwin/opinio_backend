package com.example.OpinioBackend.users.service;




import com.example.OpinioBackend.config.JwtService;
import com.example.OpinioBackend.users.model.AuthenticationRequest;
import com.example.OpinioBackend.users.model.AuthenticationResponse;
import com.example.OpinioBackend.users.model.RefreshTokenRequest;
import com.example.OpinioBackend.users.model.UserModel;
import com.example.OpinioBackend.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UserService usersService;
    private final UsersRepository usersRepository;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
        var user = usersService.findByUsername(request.getUsername());
        var jwtToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    public AuthenticationResponse refresh(RefreshTokenRequest request) {
        var username = jwtService.extractUsername(request.getRefreshToken());
        var user = userDetailsService.loadUserByUsername(username);
        if (user != null) {
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            return AuthenticationResponse.builder().refreshToken(refreshToken).accessToken(accessToken).build();
        }
        throw new BadCredentialsException("ds");


    }

    public void addUser(String username,String password){
        UserModel userModel = UserModel.builder().username(username)
                .password(passwordEncoder.encode(password))
                .build();
        usersRepository.save(userModel);
    }



}
