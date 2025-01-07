package com.literalura.literAlura.repository;

import com.literalura.literAlura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Encuentra autores vivos en un rango de fechas
    List<Autor> findByFechaNacimientoBeforeAndFechaFallecimientoAfter(LocalDate fechaInicio, LocalDate fechaFin);

    // Encuentra autores por nombre que contengan una palabra clave (mejor para búsquedas flexibles)
    List<Autor> findByNombreContainingIgnoreCase(String nombre);
}