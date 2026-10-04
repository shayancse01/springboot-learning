package com.shayancse.SecurityApp.module5SecurityApplication.filters;

import com.shayancse.SecurityApp.module5SecurityApplication.entities.User;
import com.shayancse.SecurityApp.module5SecurityApplication.services.JwtService;
import com.shayancse.SecurityApp.module5SecurityApplication.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        //Token contains the token header and the token value
        //If we don't find the token
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); //Continue processing this request with the next filter in the filter chain.
            return;
        }

        //If we find out the token
        String token = requestTokenHeader.split("Bearer ")[1];
        //actually our token is in the format of "Bearer ksdnkjsnfksnfsd" so after splitting the Array contains
        // ["Bearer"(at index 0), "value of the token"(at index 1)] so that's why for token we need to go to the 1st index

        Long userId = jwtService.getUserIdFromToken(token);

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //First get the user
            User user = userService.getUserById(userId);
            //Now Put the user in spring security context holder
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, null);
            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                    //Basically this will contain more information such as IP Address and other session ID related information
                    //It's important to set some rate limiting or protect against The DDOS attack
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //From the SecurityContextHolder we can get Authentication data(means the user details) anywhere in my app and lots of more data as well
        }
        //Now just go to the next filter in the Filter Chain
        filterChain.doFilter(request, response);
    }
}
