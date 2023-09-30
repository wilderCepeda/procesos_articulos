package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.UserModel;
import org.springframework.http.ResponseEntity;
public interface UserService {
    ResponseEntity<UserModel> createUser(UserModel user);

    ResponseEntity<?> login(String email, String password);
}
