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
import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;

    @Autowired
    public GutendexService(RestTemplate restTemplate, AutorRepository autorRepository, LibroRepository libroRepository) {
        this.restTemplate = restTemplate;
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;
        List<Libro> libros = new ArrayList<>();

        try {
            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
            if (response != null && response.getBooks() != null) {
                for (GutendexBook book : response.getBooks()) {
                    Autor autor = procesarAutor(book);
                    Integer anioPublicacion = extraerAnioDeSubjects(book.getSubjects());

                    Libro libro = new Libro(
                            book.getId(),
                            book.getTitle(),
                            autor,
                            anioPublicacion != null && anioPublicacion > 0 ? anioPublicacion : null,
                            extraerGenero(book),
                            book.getLanguages().isEmpty() ? "Sin información" : convertirIdioma(book.getLanguages().get(0))
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

    private Integer extraerAnioDeSubjects(List<String> subjects) {
        for (String subject : subjects) {
            try {
                if (subject.matches(".*\\b(\\d{4})\\b.*")) { // Busca un año válido (4 dígitos)
                    String year = subject.replaceAll(".*\\b(\\d{4})\\b.*", "$1");
                    return Integer.parseInt(year);
                }
            } catch (NumberFormatException e) {
                System.out.println("No se pudo extraer el año de: " + subject);
            }
        }
        return null; // Devuelve null si no se encuentra un año válido
    }

    private String convertirIdioma(String codigoIdioma) {
        return switch (codigoIdioma.toLowerCase()) {
            case "en" -> "Inglés";
            case "es" -> "Español";
            case "fr" -> "Francés";
            case "de" -> "Alemán";
            case "it" -> "Italiano";
            case "pt" -> "Portugués";
            default -> "Desconocido";
        };
    }

    private Autor procesarAutor(GutendexBook book) {
        if (book.getAuthors() == null || book.getAuthors().isEmpty()) {
            return autorRepository.findByNombreAndApellido("Sin información", "Desconocido")
                    .orElseGet(() -> autorRepository.save(new Autor(
                            "Sin información", // Nombre
                            "Desconocido", // Apellido
                            null, // Fecha de nacimiento
                            null, // Fecha de fallecimiento
                            "Sin información" // Nacionalidad
                    )));
        }

        var firstAuthor = book.getAuthors().get(0);
        String nombreCompleto = firstAuthor.getName();
        String[] partes = nombreCompleto.split(" ", 2); // Dividir en nombre y apellido

        // Ajustar el apellido y nombre eliminando comas y espacios adicionales
        String apellido = partes.length > 0 ? partes[0].replaceAll(",", "").trim() : "Desconocido";
        String nombre = partes.length > 1 ? partes[1].trim() : "Sin información";

        return autorRepository.findByNombreAndApellido(nombre, apellido)
                .orElseGet(() -> autorRepository.save(new Autor(
                        nombre,
                        apellido,
                        firstAuthor.getBirthYear() != null ? LocalDate.of(firstAuthor.getBirthYear(), 1, 1) : null,
                        firstAuthor.getDeathYear() != null ? LocalDate.of(firstAuthor.getDeathYear(), 1, 1) : null,
                        "Sin información"
                )));
    }

    private String extraerGenero(GutendexBook book) {
        if (book.getBookshelves() != null && !book.getBookshelves().isEmpty()) {
            return book.getBookshelves().get(0).replace("Browsing:", "").trim(); // Eliminar prefijo
        }
        return "Sin información";
    }
}