package com.literalura.literAlura.repository;

import com.literalura.literAlura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNombreContaining(String nombre);
    List<Autor> findByFechaNacimientoBeforeAndFechaFallecimientoAfter(int yearNacimiento, int yearFallecimiento);
}