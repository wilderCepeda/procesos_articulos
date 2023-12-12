package com.parcialUno.tienda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
//OBJETO - DB
public class Categoria
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @NotBlank(message = "Name category is required")
    @Size(max = 255, message = "Name category max 255 characters")
    private String nameCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    List<Articulo> articuloList;
}
