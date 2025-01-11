package com.literalura.literAlura.repository;

import com.literalura.literAlura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);
    List<Autor> findByFechaNacimientoBeforeAndFechaFallecimientoAfter(LocalDate fechaInicio, LocalDate fechaFin);
    Optional<Autor> findByNombre(String nombre);
}