package com.primer_parcial.SyK.repository;

import com.primer_parcial.SyK.models.MerchandiseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchandiseRepository extends JpaRepository<MerchandiseModel, Long> {
    Optional<MerchandiseModel> findAllByCode(String code);
    Optional<MerchandiseModel> findByCode(String code);
}
