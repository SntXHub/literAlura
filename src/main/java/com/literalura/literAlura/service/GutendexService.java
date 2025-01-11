package com.literalura.literAlura.service;

import com.literalura.literAlura.entity.Autor;
import com.literalura.literAlura.entity.GutendexBook;
import com.literalura.literAlura.entity.GutendexResponse;
import com.literalura.literAlura.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final AutorService autorService;

    @Autowired
    public GutendexService(RestTemplate restTemplate, AutorService autorService) {
        this.restTemplate = restTemplate;
        this.autorService = autorService;
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;
        try {
            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
            List<Libro> libros = new ArrayList<>();
            if (response != null && response.getBooks() != null) {
                for (GutendexBook book : response.getBooks()) {
                    // Guardar autores en la base de datos
                    if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
                        for (var gutendexAuthor : book.getAuthors()) {
                            Autor autor = new Autor(
                                    gutendexAuthor.getName(),
                                    null, // Fecha de nacimiento no disponible
                                    null, // Fecha de fallecimiento no disponible
                                    null  // Nacionalidad no disponible
                            );
                            autorService.guardarOActualizarAutor(autor);
                        }
                    }

                    // Crear objeto Libro
                    String autorNombre = (book.getAuthors() != null && !book.getAuthors().isEmpty())
                            ? book.getAuthors().get(0).getName()
                            : "Desconocido";
                    Libro libro = new Libro(
                            book.getId(),
                            book.getTitle(),
                            autorNombre,
                            null, // Año no proporcionado
                            null, // Género no proporcionado
                            book.getLanguages().isEmpty() ? "Desconocido" : book.getLanguages().get(0)
                    );
                    libros.add(libro);
                }
            }
            return libros;
        } catch (Exception e) {
            System.err.println("Error al conectar con la API de Gutendex: " + e.getMessage());
            return List.of();
        }
    }
}

