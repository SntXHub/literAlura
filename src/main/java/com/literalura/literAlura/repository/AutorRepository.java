package com.literalura.literAlura.repository;

import com.literalura.literAlura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByFechaNacimientoBeforeAndFechaFallecimientoAfter(LocalDate fechaNacimiento, LocalDate fechaFallecimiento);

    default Autor findByNombre(String autorNombre) {
        return null;
    }
}