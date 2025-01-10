package com.literalura.literAlura.service;

import com.literalura.literAlura.entity.Libro;
import com.literalura.literAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public void borrarTodosLosLibros() {
        libroRepository.deleteAll();
    }

    public void guardarLibro(Libro libro) {
        libroRepository.save(libro);
    }

    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}
