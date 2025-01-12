package com.literalura.literAlura.service;

import com.literalura.literAlura.entity.Autor;
import com.literalura.literAlura.entity.GutendexBook;
import com.literalura.literAlura.entity.GutendexResponse;
import com.literalura.literAlura.entity.Libro;
import com.literalura.literAlura.repository.AutorRepository;
import com.literalura.literAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public GutendexService(RestTemplate restTemplate, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.restTemplate = restTemplate;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;
        List<Libro> libros = new ArrayList<>();

        try {
            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
            if (response != null && response.getBooks() != null) {
                for (GutendexBook book : response.getBooks()) {
                    Autor autor = procesarAutor(book);

                    Libro libro = new Libro(
                            book.getId(),
                            book.getTitle(),
                            autor,
                            extraerAnioDeSubjects(book.getSubjects()),
                            extraerGenero(book),
                            convertirIdioma(book.getLanguages())
                    );

                    if (!libroRepository.existsById(libro.getId())) {
                        libroRepository.save(libro);
                    }
                    libros.add(libro);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con la API de Gutendex: " + e.getMessage());
        }

        return libros;
    }

    private Autor procesarAutor(GutendexBook book) {
        if (book.getAuthors() == null || book.getAuthors().isEmpty()) {
            return autorRepository.findByNombre("Desconocido")
                    .orElseGet(() -> autorRepository.save(new Autor(
                            "Desconocido",
                            "Sin información",
                            "Sin información",
                            null,
                            null,
                            "Sin información" // Nacionalidad predeterminada
                    )));
        }

        var firstAuthor = book.getAuthors().get(0);
        String nombreCompleto = firstAuthor.getName();
        String[] partes = nombreCompleto.split(",\\s*");
        String apellido = partes.length > 0 ? partes[0] : "Sin información";
        String nombre = partes.length > 1 ? partes[1] : "Desconocido";

        return autorRepository.findByNombre(nombreCompleto)
                .orElseGet(() -> autorRepository.save(new Autor(
                        nombreCompleto,
                        nombre,
                        apellido,
                        firstAuthor.getBirthYear() != null ? LocalDate.of(firstAuthor.getBirthYear(), 1, 1) : null,
                        firstAuthor.getDeathYear() != null ? LocalDate.of(firstAuthor.getDeathYear(), 1, 1) : null,
                        "Sin información" // Nacionalidad predeterminada
                )));
    }

    private Integer extraerAnioDeSubjects(List<String> subjects) {
        if (subjects == null || subjects.isEmpty()) {
            return null; // Retorna null si no hay información
        }
        for (String subject : subjects) {
            try {
                if (subject.matches(".*\\(\\d{4}\\).*")) {
                    String year = subject.replaceAll(".*\\((\\d{4})\\).*", "$1");
                    return Integer.parseInt(year);
                }
            } catch (NumberFormatException e) {
                System.out.println("No se pudo extraer el año de: " + subject);
            }
        }
        return null; // Retorna null si no se encuentra el año
    }

    private String extraerGenero(GutendexBook book) {
        if (book.getBookshelves() != null && !book.getBookshelves().isEmpty()) {
            String genero = book.getBookshelves().get(0);
            return genero.replace("Browsing:", "").trim();
        }
        return "Sin información";
    }

    private String convertirIdioma(List<String> languages) {
        if (languages != null && !languages.isEmpty()) {
            return switch (languages.get(0).toLowerCase()) {
                case "en" -> "Inglés";
                case "es" -> "Español";
                case "fr" -> "Francés";
                case "de" -> "Alemán";
                case "pt" -> "Portugués";
                default -> "Idioma desconocido";
            };
        }
        return "Idioma desconocido";
    }
}
