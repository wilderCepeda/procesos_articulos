package com.parcialUno.merchandises.controller;

import com.parcialUno.merchandises.model.MerchandiseModel;
import com.parcialUno.merchandises.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MerchandiseController {
    @Autowired
    private MerchandiseService merchandiseService;

    @GetMapping("merchandises/{id}")
    public ResponseEntity<MerchandiseModel> getMerchandiseById(@PathVariable Long id){
        return ResponseEntity.ok(merchandiseService.getMerchandiseById(id));
    }

    @PostMapping("merchandises")
    public ResponseEntity<MerchandiseModel> createMerchandise(@RequestBody MerchandiseModel merchandises){
        return new ResponseEntity<>(merchandises, HttpStatus.CREATED);
    }

    @PutMapping("merchandises/{id}")
    public ResponseEntity<MerchandiseModel> updateMerchandise(@RequestBody MerchandiseModel merchandises, @PathVariable Long id){
        return new ResponseEntity<>(merchandiseService.updateMerchandise(merchandises, id), HttpStatus.OK);
    }

    @DeleteMapping("merchandises/{id}")
    public ResponseEntity<String> deleteMerchandise(@PathVariable Long id){
        return new ResponseEntity(merchandiseService.deleteMerchandise(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("merchandises")
    public  ResponseEntity<List<MerchandiseModel>> findAllMerchandises(){
        return ResponseEntity.ok(merchandiseService.findAllMerchandises());
    }
}
