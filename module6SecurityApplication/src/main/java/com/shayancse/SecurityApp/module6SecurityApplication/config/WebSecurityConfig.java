package com.shayancse.SecurityApp.module6SecurityApplication.config;

import com.shayancse.SecurityApp.module6SecurityApplication.filters.JwtAuthFilter;
import com.shayancse.SecurityApp.module6SecurityApplication.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.shayancse.SecurityApp.module6SecurityApplication.entities.enums.Permission.*;
import static com.shayancse.SecurityApp.module6SecurityApplication.entities.enums.Role.ADMIN;
import static com.shayancse.SecurityApp.module6SecurityApplication.entities.enums.Role.CREATOR;

@Configuration
@EnableWebSecurity //By this we are telling spring-boot that we are configuring spring security filter chain.
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private static final String[] publicRoutes = {
            "/error", "/auth/**", "/home.html"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                //For authorization of requests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicRoutes).permitAll() //Means these routes are public for anyone
                        .requestMatchers("/posts/**").authenticated()
                        .anyRequest().authenticated())

                //For the CSRF Token
                .csrf(csrfConfig -> csrfConfig.disable())

                //For the session management
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //Add our custom jwtAuthFilter just before the UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2Config -> oauth2Config
                        .failureUrl("/login?error=true")
                        .successHandler(oAuth2SuccessHandler));

                //.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//    We cannot have two beans with same name as UserDetailsService
//    @Bean
//    UserDetailsService myInMemoryUserDetailsService() {
//        UserDetails normalUser = User
//                .withUsername("shayan")
//                .password(passwordEncoder().encode("shayan123"))
//                .roles("USER")
//                .build();
//
//        UserDetails adminUser = User
//                .withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }
}
