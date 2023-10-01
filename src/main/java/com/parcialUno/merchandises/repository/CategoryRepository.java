package com.parcialUno.merchandises.repository;

import com.parcialUno.merchandises.model.CategoryModel;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryModel, Long> {
}
