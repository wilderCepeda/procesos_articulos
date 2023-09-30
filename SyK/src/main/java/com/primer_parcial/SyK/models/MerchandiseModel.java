package com.primer_parcial.SyK.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "merchandise")

public class MerchandiseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String code;
    @Column(length = 300, nullable = false)
    private String description;
    @Column(length = 100, nullable = false)
    private Date registerDate;
    @ManyToOne
    private CategoryModel category;
    @Column(length = 100)
    private Integer stock;
    @Column(length=400, nullable= false)
    private Float purchase_price;
    @Column(length=400, nullable= false)
    private Float sale_price;

}
