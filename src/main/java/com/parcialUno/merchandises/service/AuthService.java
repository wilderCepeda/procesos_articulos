package com.parcialUno.merchandises.service;

import com.parcialUno.merchandises.LoginRequest;
import com.parcialUno.merchandises.model.UserModel;
import com.parcialUno.merchandises.repository.UserRepository;
import com.parcialUno.merchandises.utils.JWTUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    public Map<String, Object> login(LoginRequest loginRequest) {
        Map<String, Object> result = new HashMap<>();

        UserModel user = userRepository.findByEmail(loginRequest.getEmail().toLowerCase(Locale.ROOT));
        if (user == null) {
            result.put("message", "Credenciales incorrectas");
            return result;
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            result.put("message", "Credenciales incorrectas;");
            return result;
        }

        String token = jwtUtil.create(String.valueOf(user.getId()), user.getEmail());

        result.put("message", "Success");
        result.put("token", token);
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities.stream().distinct().collect(Collectors.toList()));
    }
    public UserModel getUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername());
    }
}
