package com.parcialUno.tienda.controller;


import com.parcialUno.tienda.model.Address;
import com.parcialUno.tienda.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController
{
    @Autowired
    private AddressService addressService;

    @GetMapping("address/{id}")
    public ResponseEntity getAddressById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddressById(id));
    }


    @PostMapping("address/{idUser}")
    public ResponseEntity<Address> create(@Valid @RequestBody Address address, @PathVariable Long idUser){
        return new ResponseEntity<>(addressService.createAddress(address, idUser), HttpStatus.CREATED);
    }

    @GetMapping("address/disable/{id}")
    public ResponseEntity disable(@PathVariable Long id)
    {
        return ResponseEntity.ok(addressService.updateStatusAddress(id));
    }

    @GetMapping("address")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(addressService.findAllAddress());
    }
}