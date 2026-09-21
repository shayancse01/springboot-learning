package com.shayancse.SecurityApp.module5SecurityApplication.controllers;

import com.shayancse.SecurityApp.module5SecurityApplication.dto.LoginDTO;
import com.shayancse.SecurityApp.module5SecurityApplication.dto.SignUpDTO;
import com.shayancse.SecurityApp.module5SecurityApplication.dto.UserDTO;
import com.shayancse.SecurityApp.module5SecurityApplication.services.AuthService;
import com.shayancse.SecurityApp.module5SecurityApplication.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO userDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request,
                                        HttpServletResponse response) {
        String token = authService.login(loginDTO);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true); //So that only Http methods can access this, javascript cann't
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
