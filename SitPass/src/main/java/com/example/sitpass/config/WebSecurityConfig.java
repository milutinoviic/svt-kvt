package com.example.sitpass.config;

import com.example.sitpass.security.auth.RestAuthenticationEntryPoint;
import com.example.sitpass.security.auth.TokenAuthenticationFilter;
import com.example.sitpass.service.implementation.CustomUserDetailsService;
import com.example.sitpass.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new PlainPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/exercise/**").permitAll()
                        .requestMatchers("/reviews/**").permitAll()
                        .requestMatchers("/facility/**").permitAll()
                        .requestMatchers("/accountRequest/**").permitAll()
                        .requestMatchers("/disciplines/**").permitAll()
                        .requestMatchers("/manages/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/comment/**").permitAll()
                        .anyRequest()
                        .authenticated()).exceptionHandling(exp -> exp.authenticationEntryPoint(restAuthenticationEntryPoint));
        http.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userDetailsService), BasicAuthenticationFilter.class);

        return http.build();
    }
}
