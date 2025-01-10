package com.literalura.literAlura.service;

import com.literalura.literAlura.entity.Libro;
import com.literalura.literAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final LibroRepository libroRepository;

    @Autowired
    public GutendexService(RestTemplate restTemplate, LibroRepository libroRepository) {
        this.restTemplate = restTemplate;
        this.libroRepository = libroRepository;
    }

    // Método para obtener libros por título desde la API de Gutendex y guardarlos en la base de datos
    public List<Libro> obtenerLibrosPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        if (response != null && response.getLibros() != null) {
            List<Libro> libros = response.getLibros();
            libroRepository.saveAll(libros); // Guardar los libros en la base de datos
            return libros;
        }
        return List.of(); // Retorna una lista vacía si no hay resultados
    }
}