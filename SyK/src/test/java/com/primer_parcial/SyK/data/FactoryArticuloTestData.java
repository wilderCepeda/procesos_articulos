package com.primer_parcial.SyK.data;

import com.primer_parcial.SyK.models.Articulo;

import java.util.Date;

import static com.primer_parcial.SyK.data.FactoryCtaegoriaTestData.mockCategoria;

public class FactoryArticuloTestData {


    public static Articulo mockArticulo() {
        Articulo modelo = new Articulo();
        modelo.setId(1L);
        modelo.setCodigo("123");
        modelo.setNombre("Limpido");
        modelo.setStock(2);
        modelo.setCategoria(mockCategoria());
        modelo.setDescripcion("HCL");
        modelo.setPrecio_venta(2000F);
        modelo.setPrecio_compra(5000F);
        modelo.setFechaRegistro(new Date(10,10,20));

        return modelo;
    }
    public static Articulo mockArticuloMod() {
        Articulo modelo = new Articulo();
        modelo.setId(1L);
        modelo.setCodigo("1");
        modelo.setNombre("Lejia");
        modelo.setStock(2);
        modelo.setCategoria(mockCategoria());
        modelo.setDescripcion("HCL");
        modelo.setPrecio_venta(2000F);
        modelo.setPrecio_compra(5000F);
        modelo.setFechaRegistro(new Date(10,10,20));

        return modelo;
    }

}
