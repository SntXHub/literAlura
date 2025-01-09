package com.literalura.literAlura.repository;

import com.literalura.literAlura.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Método válido para buscar por título ignorando mayúsculas y minúsculas
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}