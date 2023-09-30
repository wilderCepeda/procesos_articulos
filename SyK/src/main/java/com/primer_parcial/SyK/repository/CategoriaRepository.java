package com.primer_parcial.SyK.repository;

import com.primer_parcial.SyK.models.Articulo;
import com.primer_parcial.SyK.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
