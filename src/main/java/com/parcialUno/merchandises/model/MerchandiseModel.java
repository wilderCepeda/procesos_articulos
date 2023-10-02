package com.parcialUno.merchandises.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MerchandiseModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;
    private String nombre;
    private String code;
    private String description;
    private String registerDate;
    private String stock;
    private String purchasePrice;
    private String salePrice;
}
