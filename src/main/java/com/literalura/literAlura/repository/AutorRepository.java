package com.literalura.literAlura.repository;

import com.literalura.literAlura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE UPPER(a.nombre) = UPPER(?1) AND UPPER(a.apellido) = UPPER(?2)")
    Optional<Autor> findByNombreAndApellido(String nombre, String apellido);

    List<Autor> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);
}
