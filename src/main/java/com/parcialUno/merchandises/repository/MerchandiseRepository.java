package com.parcialUno.merchandises.repository;

import com.parcialUno.merchandises.model.MerchandiseModel;
import org.springframework.data.repository.CrudRepository;

public interface MerchandiseRepository extends CrudRepository<MerchandiseModel, Long> {
}
