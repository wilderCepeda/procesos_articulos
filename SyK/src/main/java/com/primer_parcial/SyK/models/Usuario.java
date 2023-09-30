package com.primer_parcial.SyK.models;

import lombok.*;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 45,nullable= false)
    private String correo;
    @Column(length = 64,nullable = false)
    private String password;

}
