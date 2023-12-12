package com.parcialUno.tienda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "street Address REQUIRED")
    @Size(max = 255, message = "streetAddress max 255 characters")
    private String streetAddress;

    @NotBlank(message = "city is REQUIRED")
    @Size(max =  255, message = "city max 255 characters")
    private String city;

    @NotBlank(message = "state is REQUIRED")
    @Size(max =  100, message = "state max 100 characters")
    private String state;

    @NotBlank(message = "state is REQUIRED")
    @Size(max =  10, message = "postalCode max 10 characters")
    private String postalCode;
    private Boolean status = Boolean.TRUE;

    @ManyToOne()
    @JoinColumn(name="id_user", referencedColumnName = "id")
    private User user;
}