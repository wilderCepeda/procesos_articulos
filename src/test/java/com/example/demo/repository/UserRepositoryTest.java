package com.example.demo.repository;

import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {//test de repositorios

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void existRepository() {
        assertNotNull(userRepository);
    }//identificando si existe el repositorio

    @Test
    void existPassEncoder() {
        assertNotNull(passwordEncoder);
    }//identificando si encuentra el bean de password encoder

    @Test
    void should_find_no_users_if_repository_is_empty() {
        List<User> users = userRepository.findAll();//si el repositorio esta vacio no deberia encontrar ningun usuario
        assertNotNull(users);
    }

    @Test
    void should_store_a_user() {//debe almacenar los usuarios
        List<User> users = new ArrayList<>();
        for (int i = 0; i<=1000; i++) {
            User user = new User();
            user.setEmail("agperezb"+ i + "@ufpso.edu.co");
            user.setPassword("123456");
            user.setFirstName("Angel"+ i);
            user.setLastName("Perez"+ i);
            user.setAddress("Rio de oro"+ i);
            user.setPhoneNumber("3101"+ i);
            users.add(user);
        }

        userRepository.saveAll(users);

        assertNotNull(userRepository.findAll());
    }

    @Test
    void should_delete_all_categories() {//debe eliminar todas las categorías.
        userRepository.deleteAll();

        assertThat(userRepository.findAll()).isEmpty();
    }

}
