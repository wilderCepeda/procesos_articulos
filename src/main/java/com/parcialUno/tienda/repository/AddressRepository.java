package com.parcialUno.tienda.repository;

import com.parcialUno.tienda.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}