package com.primer_parcial.SyK.repository;

import com.primer_parcial.SyK.models.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    Optional<Articulo> findAllByCodigo(String codigo);
    Optional<Articulo> findByCodigo(String codigo);
}
