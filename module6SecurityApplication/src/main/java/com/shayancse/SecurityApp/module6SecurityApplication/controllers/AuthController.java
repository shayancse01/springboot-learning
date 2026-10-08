package com.shayancse.SecurityApp.module6SecurityApplication.controllers;

import com.shayancse.SecurityApp.module6SecurityApplication.dto.LoginDTO;
import com.shayancse.SecurityApp.module6SecurityApplication.dto.LoginResponseDTO;
import com.shayancse.SecurityApp.module6SecurityApplication.dto.SignUpDTO;
import com.shayancse.SecurityApp.module6SecurityApplication.dto.UserDTO;
import com.shayancse.SecurityApp.module6SecurityApplication.services.AuthService;
import com.shayancse.SecurityApp.module6SecurityApplication.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO userDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request,
                                        HttpServletResponse response) {
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);

        Cookie cookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true); //So that only Http methods can access this, javascript cann't

        cookie.setSecure("production".equals(deployEnv)); //If the environment is production then only set the cookies secure
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie :: getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh Token not found inside the cookies"));

        LoginResponseDTO loginResponseDTO = authService.refreshTokenMethod(refreshToken);
        return ResponseEntity.ok(loginResponseDTO);
    }
}
