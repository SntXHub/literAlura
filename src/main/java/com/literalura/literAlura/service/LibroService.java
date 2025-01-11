package com.literalura.literAlura.service;

import com.literalura.literAlura.entity.Libro;
import com.literalura.literAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public LibroService(LibroRepository libroRepository, RestTemplate restTemplate) {
        this.libroRepository = libroRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public List<Libro> buscarOLlenarPorTitulo(String titulo) {
        // Buscar en la base de datos local
        List<Libro> librosLocales = libroRepository.findByTituloContainingIgnoreCase(titulo);

        if (!librosLocales.isEmpty()) {
            return librosLocales; // Si ya existen, retornarlos
        }

        // Buscar en la API externa
        String url = "https://gutendex.com/books/?search=" + titulo;
        try {
            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

            if (response != null && response.getLibros() != null) {
                List<Libro> librosApi = response.getLibros();

                for (Libro libro : librosApi) {
                    // Validar y guardar si no existe
                    if (!libroRepository.existsById(libro.getId())) {
                        libroRepository.save(libro);
                    }
                }

                return libroRepository.findByTituloContainingIgnoreCase(titulo); // Refrescar desde la BD
            }
        } catch (Exception e) {
            System.err.println("Error al conectarse con la API de Gutendex: " + e.getMessage());
        }

        return List.of(); // Retornar lista vacía si no se encontraron resultados
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }

    @Transactional
    public void eliminarLibroPorId(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("No se encontró un libro con el ID especificado.");
        }
        libroRepository.deleteById(id);
    }
}
