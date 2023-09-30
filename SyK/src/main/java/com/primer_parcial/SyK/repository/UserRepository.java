package com.primer_parcial.SyK.repository;

import com.primer_parcial.SyK.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(String email);

}

