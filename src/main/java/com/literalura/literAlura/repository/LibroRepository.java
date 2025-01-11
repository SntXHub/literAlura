package com.literalura.literAlura.repository;

import com.literalura.literAlura.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Método para buscar libros por idioma
    List<Libro> findByIdiomaIgnoreCase(String idioma);

    // Otros métodos personalizados que puedas necesitar
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}