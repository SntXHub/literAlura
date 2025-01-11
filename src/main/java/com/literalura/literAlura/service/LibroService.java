package com.literalura.literAlura.service;

import com.literalura.literAlura.entity.Libro;
import com.literalura.literAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final GutendexService gutendexService;

    @Autowired
    public LibroService(LibroRepository libroRepository, GutendexService gutendexService) {
        this.libroRepository = libroRepository;
        this.gutendexService = gutendexService;
    }

    /**
     * Busca libros por título en la base de datos local. Si no se encuentran resultados,
     * realiza la búsqueda en la API externa de Gutendex y guarda los resultados en la base de datos.
     *
     * @param titulo el título del libro a buscar
     * @return una lista de libros encontrados
     */
    public List<Libro> buscarOLlenarPorTitulo(String titulo) {
        try {
            // Buscar libros en la base de datos local
            List<Libro> librosLocales = libroRepository.findByTituloContainingIgnoreCase(titulo);
            if (!librosLocales.isEmpty()) {
                return librosLocales;
            }

            // Buscar libros en la API de Gutendex
            List<Libro> librosExternos = gutendexService.buscarLibrosPorTitulo(titulo);

            if (librosExternos.isEmpty()) {
                return List.of(); // Retornar una lista vacía si no hay resultados
            }

            // Guardar los libros externos en la base de datos si no existen
            for (Libro libro : librosExternos) {
                if (!libroRepository.existsById(libro.getId())) {
                    libroRepository.save(libro);
                }
            }

            return librosExternos;

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar libros: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id el ID del libro a eliminar
     */
    public void eliminarLibroPorId(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el libro con ID: " + id);
        }
        libroRepository.deleteById(id);
    }

    /**
     * Obtiene todos los libros registrados en la base de datos.
     *
     * @return una lista de todos los libros
     */
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    /**
     * Obtiene libros registrados en la base de datos por idioma.
     *
     * @param idioma el idioma de los libros a buscar
     * @return una lista de libros en el idioma especificado
     */
    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }
}
