package com.literalura.literAlura.service;

import com.literalura.literAlura.entity.Libro;
import com.literalura.literAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma); // Usa el método correcto
    }

    public void eliminarLibroPorId(Long id) {
        libroRepository.deleteById(id);
    }
}
