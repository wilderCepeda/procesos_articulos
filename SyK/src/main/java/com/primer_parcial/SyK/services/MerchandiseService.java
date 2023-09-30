package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.MerchandiseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MerchandiseService {

    ResponseEntity<MerchandiseModel> getMerchandiseByCod(String code);
    ResponseEntity<List<MerchandiseModel>> getAllMerchandises();
    ResponseEntity<MerchandiseModel> createMerchandise(MerchandiseModel merchandise);
    ResponseEntity<MerchandiseModel> editMerchandise(String code, MerchandiseModel merchandise);
    ResponseEntity<MerchandiseModel> deleteMerchandise(String code);


}
