package com.primer_parcial.Merchandise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        RequestMatcher publicUrls = new OrRequestMatcher(
                new AntPathRequestMatcher("/usuario", HttpMethod.POST.toString()),
                new AntPathRequestMatcher("/auth/login", HttpMethod.POST.toString())
        );
        http.authorizeRequests()
                .requestMatchers(publicUrls).permitAll()
                .anyRequest().authenticated();
        return http.build();
    }
}