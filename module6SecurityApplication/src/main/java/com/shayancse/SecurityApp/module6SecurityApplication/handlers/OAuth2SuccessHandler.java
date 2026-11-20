package com.shayancse.SecurityApp.module6SecurityApplication.handlers;

import com.shayancse.SecurityApp.module6SecurityApplication.entities.User;
import com.shayancse.SecurityApp.module6SecurityApplication.services.JwtService;
import com.shayancse.SecurityApp.module6SecurityApplication.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Value("${deploy.env}")
    private String deployEnv;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {


        //First Get the access token, coming from google
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        //Get the user information from that token
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();

        //Extract the email from it, to identify the user
        String email = oAuth2User.getAttribute("email");
        User user = userService.getUserByEmail(email);

        //if the user is not present then create the user first
        if (user == null) {
            User newUser = User.builder()
                    .name(oAuth2User.getName())
                    .email(email)
                    .build();
            user = userService.save(newUser);
        }

        //Now just generate the access and refresh token and save the refresh token inside the cookie
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);

        cookie.setSecure("production".equals(deployEnv));
        response.addCookie(cookie);

        //After Successfully authentication, redirect it to the home.html page with the access token (created under resources/static)
        String frontEndUrl = "http://localhost:8080/home.html?token=" + accessToken;
        //sending the request to this particular url(frontEndUrl)
        //getRedirectStrategy().sendRedirect(request, response, frontEndUrl);

        //sending the request to this particular url(frontEndUrl)
        response.sendRedirect(frontEndUrl);
    }
}
