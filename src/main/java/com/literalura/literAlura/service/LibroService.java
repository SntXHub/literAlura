package com.literalura.literAlura.service;

import com.literalura.literAlura.entity.Autor;
import com.literalura.literAlura.entity.Libro;
import com.literalura.literAlura.repository.AutorRepository;
import com.literalura.literAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.client.RestTemplate;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    // Buscar libros por título
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContaining(titulo);
    }

    // Obtener todos los libros
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    // Obtener todos los autores
    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }

    // Obtener autores vivos en un año
    public List<Autor> obtenerAutoresVivosEnAnio(LocalDate anio) {
        return autorRepository.findByFechaNacimientoBeforeAndFechaFallecimientoAfter(anio, anio);
    }

    // Obtener libros por idioma
    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}

