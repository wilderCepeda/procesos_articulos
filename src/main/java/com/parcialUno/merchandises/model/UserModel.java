package com.parcialUno.merchandises.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "users")
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    @Column(name = "address", nullable = false, length = 300)
    private String address;
    @Column(name = "dni", nullable = false, length = 20)
    private String dni;
    @Column(name = "phone", nullable = false, length = 10)
    private String phone;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<MerchandiseModel> merchandises;
}
