package com.primer_parcial.Merchandise.data;


import com.primer_parcial.Merchandise.models.Categoria;

public class FactoryCtaegoriaTestData {

    public static Categoria mockCategoria() {
        Categoria modelo = new Categoria();
        modelo.setId(1L);
        modelo.setNombre("Limpiza");
        modelo.setDescripcion("Productos detergetes");

        return modelo;
    }

}
