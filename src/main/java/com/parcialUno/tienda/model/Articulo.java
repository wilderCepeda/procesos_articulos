package com.parcialUno.tienda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name article is required")
    @Size(max = 255, message = "Name article max 255 characters")
    private String name;
    @NotNull(message = "Description is required")
    @Size(max = 400, message = "lastname max 400 characters")
    private String description;
    @NotNull(message = "Price is required")
    @Min(value= 0, message = "price > 0")
    private Double price;
    @NotNull(message = "Stock is required")
    @Min(value= 0, message = "stock >= 0")
    private int stock;

    /*@Lob
    @Column(name = "images", columnDefinition = "BLOB")
    private byte[] image;*/

    @ManyToOne()
    @JoinColumn(name = "id_category", referencedColumnName = "idCategory")
    private Categoria categoria;
}
