package com.primer_parcial.SyK.repository;

import com.primer_parcial.SyK.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

}
