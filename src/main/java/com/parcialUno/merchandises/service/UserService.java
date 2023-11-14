package com.parcialUno.merchandises.service;

import com.parcialUno.merchandises.model.UserModel;
import com.parcialUno.merchandises.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserModel createUser(UserModel userReq){
        return userRepository.save(userReq);
    }

    public UserModel getUserById(Long id){
        return userRepository.findById(id).get();
    }
    public UserModel updateUser(UserModel userReq, Long id){
        Optional<UserModel> userDB = userRepository.findById(id);
        if(userDB.isEmpty()){
            return null;
        }
        userDB.get().setFirstName(userReq.getFirstName());
        userDB.get().setLastName(userReq.getLastName());
        userDB.get().setAddress(userReq.getAddress());
        userDB.get().setPhone(userReq.getPhone());
        return userRepository.save(userDB.get());
    }

    public boolean deleteUser(Long id){
        Optional<UserModel> userDB = userRepository.findById(id);
        if(userDB.isEmpty()){
            return false;
        }
        userRepository.delete(userDB.get());
        return true;
    }

    public List<UserModel> findAllUsers(){
        return (List<UserModel>) userRepository.findAll();
    }
}
