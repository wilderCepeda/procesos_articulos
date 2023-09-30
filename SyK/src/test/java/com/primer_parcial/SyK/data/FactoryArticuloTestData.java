package com.primer_parcial.SyK.data;

import com.primer_parcial.SyK.models.MerchandiseModel;

import java.util.Date;

import static com.primer_parcial.SyK.data.FactoryCtaegoriaTestData.mockCategoria;

public class FactoryMerchandiseTestData {


    public static MerchandiseModel mockMerchandise() {
        MerchandiseModel model = new MerchandiseModel();
        model.setId(1L);
        model.setCode("123");
        model.setName("Limpido");
        model.setStock(2);
        model.setCategory(mockCategory());
        model.setDescription("HCL");
        model.setSale_price(2000F);
        model.setPurchase_price(5000F);
        model.setRegisterDate(new Date(10,10,20));

        return model;
    }
    public static MerchandiseModel mockMerchandiseMod() {
        MerchandiseModel model = new MerchandiseModel();
        model.setId(1L);
        model.setCode("1");
        model.setName("Lejia");
        model.setStock(2);
        model.setCategory(mockCategoria());
        model.setDescription("HCL");
        model.setSale_price(2000F);
        model.setPurchase_price(5000F);
        model.setRegisterDate(new Date(10,10,20));

        return model;
    }

}
