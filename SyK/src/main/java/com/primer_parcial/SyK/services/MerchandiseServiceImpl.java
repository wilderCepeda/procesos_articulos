package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.MerchandiseModel;
import com.primer_parcial.SyK.models.CategoryModel;
import com.primer_parcial.SyK.repository.MerchandiseRepository;
import com.primer_parcial.SyK.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {

    @Autowired
    private MerchandiseRepository merchandiseRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity getMerchandiseByCod(@PathVariable String code) {
        Optional<MerchandiseModel> merchandise = merchandiseRepository.findAllByCode(code);//Cambiar el Find ya que son todos los datos
        if (merchandise.isPresent()) {
            return new ResponseEntity(merchandise, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<MerchandiseModel>> getAllMerchandises() {
        List<MerchandiseModel> merchandise = merchandiseRepository.findAll();
        if (merchandise.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(merchandise, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MerchandiseModel> createMerchandise(MerchandiseModel merchandise) {
        Long idC = merchandise.getCategory().getId();
        Optional<CategoryModel> merchandiseBD = categoryRepository.findById(idC);
        if (merchandiseBD.isPresent()) {
            try {

                merchandiseRepository.save(merchandise);
                return new ResponseEntity(merchandise, HttpStatus.CREATED);
            } catch (Exception e) {
                System.out.print(e.fillInStackTrace());
                return ResponseEntity.badRequest().build();
            }
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<MerchandiseModel> editMerchandise(String code, MerchandiseModel merchandise) {
        Optional<MerchandiseModel> merchandiseBD = merchandiseRepository.findByCode(code);
        if (merchandiseBD.isPresent()) {
            try {
                merchandiseBD.get().setName(merchandise.getName());
                merchandiseBD.get().setDescription(merchandise.getDescription());
                merchandiseBD.get().setRegisterDate(merchandise.getRegisterDate());
                merchandiseBD.get().setCategory(merchandiseBD.get().getCategory());
                merchandiseBD.get().setStock(merchandise.getStock());
                merchandiseBD.get().setSale_price(merchandise.getSale_price());
                merchandiseBD.get().setPurchase_price(merchandise.getPurchase_price());
                merchandiseRepository.save(merchandiseBD.get());
                return new ResponseEntity(merchandise, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<MerchandiseModel> deleteMerchandise(String code) {
        Optional<MerchandiseModel> merchandiseBD = merchandiseRepository.findByCode(code);
        if (merchandiseBD.isPresent()){
            merchandiseRepository.delete(merchandiseBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
