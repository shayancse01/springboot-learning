package com.shayancse.SecurityApp.module6SecurityApplication.services;

import com.shayancse.SecurityApp.module6SecurityApplication.dto.LoginDTO;
import com.shayancse.SecurityApp.module6SecurityApplication.dto.LoginResponseDTO;
import com.shayancse.SecurityApp.module6SecurityApplication.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;


    public LoginResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }

    //Method for generating a access token through a refresh token
    public LoginResponseDTO refreshTokenMethod(String refreshToken) {
        //First check the refresh token we are getting is valid or not?
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        //getUserIdFromToken will automatically valided the token

        User user = userService.getUserById(userId);
        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }
}
