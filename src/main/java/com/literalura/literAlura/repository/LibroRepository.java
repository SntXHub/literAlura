package com.literalura.literAlura.repository;

import com.literalura.literAlura.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros que contengan el título indicado
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Buscar libros por idioma
    List<Libro> findByIdioma(String idioma);
}