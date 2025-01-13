package com.literalura.literAlura.repository;

import com.literalura.literAlura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar un autor por nombre exacto
    Optional<Autor> findByNombre(String nombre);

    // Buscar autores por nombre o apellido (ignorando mayúsculas y tildes) y eliminando duplicados
    @Query("SELECT DISTINCT a FROM Autor a WHERE UPPER(a.nombre) LIKE UPPER(CONCAT('%', :criterio, '%')) OR UPPER(a.apellido) LIKE UPPER(CONCAT('%', :criterio, '%'))")
    List<Autor> findDistinctByNombreOrApellidoContainingIgnoreCase(@Param("criterio") String criterio);

    // Obtener todos los autores sin duplicados
    @Query("SELECT DISTINCT a FROM Autor a")
    List<Autor> findAllDistinct();

    // Encontrar autores vivos en un año específico (vivos si fecha de fallecimiento es nula o posterior al año)
    @Query("SELECT DISTINCT a FROM Autor a WHERE a.fechaNacimiento <= :fechaFin AND (a.fechaFallecimiento IS NULL OR a.fechaFallecimiento > :fechaInicio)")
    List<Autor> findAutoresVivosEnAnio(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
}