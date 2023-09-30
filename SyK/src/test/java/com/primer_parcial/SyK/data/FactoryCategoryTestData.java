package com.primer_parcial.SyK.data;


import com.primer_parcial.SyK.models.CategoryModel;

public class FactoryCategoryTestData {

    public static CategoryModel mockCategory() {
        CategoryModel model = new CategoryModel();
        model.setId(1L);
        model.setName("Limpiza");
        model.setDescription("Productos detergetes");

        return model;
    }

}
