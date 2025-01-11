package com.literalura.literAlura.service;

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

    @Autowired
    public GutendexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Libro convertirAGutendexBook(GutendexBook book) {
        String autor = book.getAuthors() != null && !book.getAuthors().isEmpty()
                ? book.getAuthors().get(0).getName()
                : "Desconocido";

        Integer anioPublicacion = book.getSubjects() != null && !book.getSubjects().isEmpty()
                ? extraerAnioDeSubject(book.getSubjects())
                : -1;

        return new Libro(
                book.getId(),
                book.getTitle(),
                autor,
                anioPublicacion,
                book.getBookshelves() != null && !book.getBookshelves().isEmpty()
                        ? (String) book.getBookshelves().get(0)
                        : "Género desconocido",
                book.getLanguages().get(0).toUpperCase()
        );
    }

    private Integer extraerAnioDeSubject(List<String> subjects) {
        return 0;
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;
        try {
            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
            List<Libro> libros = new ArrayList<>();
            if (response != null && response.getBooks() != null) {
                for (GutendexBook book : response.getBooks()) {
                    String autor = book.getAuthors().isEmpty() ? "Desconocido" : book.getAuthors().get(0).getName();
                    libros.add(new Libro(
                            book.getId(),
                            book.getTitle(),
                            autor,
                            null, // Año de publicación (no proporcionado)
                            null, // Género (no proporcionado)
                            book.getLanguages().isEmpty() ? "Desconocido" : book.getLanguages().get(0)
                    ));
                }
            }
            return libros;
        } catch (Exception e) {
            System.err.println("Error al conectar con la API de Gutendex: " + e.getMessage());
            return List.of();
        }
    }
}