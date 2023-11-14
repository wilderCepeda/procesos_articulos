package com.parcialUno.merchandises.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcialUno.merchandises.filter.JwtRequestFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Deprecated
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] ROUTES_ALLOWED_WITHOUT_AUTHENTICATION = {
    };

    private final String[] ROUTES_GET_ALLOWED_WITHOUT_AUTHENTICATION = {
            "/user/forgot_password/{email}",
    };
    private final String[] ROUTES_POST_ALLOWED_WITHOUT_AUTHENTICATION = {
            "/api/auth/login",
            "/api/user",
            "/user/reset_password",
    };

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return null;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return false;
            }
        };
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        };
    }
    private final JwtRequestFilter jwtRequestFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers(ROUTES_ALLOWED_WITHOUT_AUTHENTICATION).permitAll()
                .requestMatchers(HttpMethod.GET, ROUTES_GET_ALLOWED_WITHOUT_AUTHENTICATION).permitAll()
                .requestMatchers(HttpMethod.POST, ROUTES_POST_ALLOWED_WITHOUT_AUTHENTICATION).permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling(exception -> exception.authenticationEntryPoint((request, response, authException) -> {
                            Map<String, Object> responseMap = new HashMap<>();
                            ObjectMapper mapper = new ObjectMapper();
                            response.setStatus(401);
                            responseMap.put("message", "Acceso no autorizado");
                            response.setHeader("Content-Type", "application/json");
                            String responseMsg = mapper.writeValueAsString(responseMap);
                            response.getWriter().write(responseMsg);
                        })).sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));//headers().frameOptions().sameOrigin();

        return http.build();
    }
}
