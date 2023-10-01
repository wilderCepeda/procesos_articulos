package com.parcialUno.merchandises.service;

import com.parcialUno.merchandises.model.MerchandiseModel;
import com.parcialUno.merchandises.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MerchandiseService {
    @Autowired
    private MerchandiseRepository merchandiseRepository;

    public MerchandiseModel createMerchandise(MerchandiseModel merchandiseReq){
        return merchandiseRepository.save(merchandiseReq);
    }
    public MerchandiseModel getMerchandiseById(Long id){
        return merchandiseRepository.findById(id).get();
    }
    public MerchandiseModel updateMerchandise(MerchandiseModel merchandiseReq, Long id){
        Optional<MerchandiseModel> merchandiseDB = merchandiseRepository.findById(id);
        if(merchandiseDB.isEmpty()){
            return null;
        }
        merchandiseDB.get().setCode(merchandiseReq.getCode());
        merchandiseDB.get().setDescription(merchandiseReq.getDescription());
        merchandiseDB.get().setRegisterDate(merchandiseReq.getRegisterDate());
        merchandiseDB.get().setStock(merchandiseReq.getStock());
        merchandiseDB.get().setPurchasePrice(merchandiseReq.getPurchasePrice());
        merchandiseDB.get().setSalePrice(merchandiseReq.getSalePrice());
        return merchandiseRepository.save(merchandiseDB.get());
    }

    public boolean deleteMerchandise(Long id){
        Optional<MerchandiseModel> merchandiseDB = merchandiseRepository.findById(id);
        if(merchandiseDB.isEmpty()){
            return false;
        }
        merchandiseRepository.delete(merchandiseDB.get());
        return true;
    }

    public List<MerchandiseModel> findAllMerchandises(){
        return (List<MerchandiseModel>) merchandiseRepository.findAll();
    }
}
