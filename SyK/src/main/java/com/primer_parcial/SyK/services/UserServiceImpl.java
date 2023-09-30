package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.UserModel;
import com.primer_parcial.SyK.repository.UserRepository;
import com.primer_parcial.SyK.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public ResponseEntity<UserModel> createUser(UserModel user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> login(String email, String password) {
        try{
            UserModel user = userRepository.findByEmail(email);
            if(passwordEncoder.matches(password, user.getPassword())){
                String token = jwtUtil.create(String.valueOf(user.getId()), user.getEmail());
                return ResponseEntity.ok(token);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

}

