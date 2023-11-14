package com.parcialUno.merchandises.repository;

import com.parcialUno.merchandises.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findByEmail(String email);
}
