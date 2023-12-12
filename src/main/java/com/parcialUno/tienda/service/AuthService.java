package com.parcialUno.tienda.service;


import com.parcialUno.tienda.dto.AuthResponse;
import com.parcialUno.tienda.dto.LoginRequest;
import com.parcialUno.tienda.exception.AlreadyExistsException;
import com.parcialUno.tienda.exception.AuthenticationFailedException;
import com.parcialUno.tienda.exception.NotFoundException;
import com.parcialUno.tienda.model.Role;
import com.parcialUno.tienda.model.User;
import com.parcialUno.tienda.repository.UserRepository;
import com.parcialUno.tienda.util.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(@Valid User request) {
        Optional<User> existingUserByEmail = userRepository.findByEmail(request.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyExistsException(Constants.USER_EMAIL_EXISTS.getMessage());
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .document(request.getDocument())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new AuthenticationFailedException(Constants.CREDENTIAL_INVALID.getMessage());
        }
        UserDetails user = userRepository.findByEmail(request.getEmail()).
            orElseThrow(() -> new NotFoundException(Constants.CREDENTIAL_INVALID.getMessage()));
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
