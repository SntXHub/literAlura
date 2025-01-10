package com.literalura.literAlura.controller;

import com.literalura.literAlura.dto.AutorDTO;
import com.literalura.literAlura.entity.Libro;
import com.literalura.literAlura.service.AutorService;
import com.literalura.literAlura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Controller
public class MenuController {

    private final AutorService autorService;
    private final LibroService libroService;

    @Autowired
    public MenuController(AutorService autorService, LibroService libroService) {
        this.autorService = autorService;
        this.libroService = libroService;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n*** Menú Principal ***");
            System.out.println("1. Buscar libros por título");
            System.out.println("2. Buscar autor por nombre");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Mostrar todos los autores");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Listar autores vivos en un año específico");
            System.out.println("7. Eliminar un libro por ID");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine()); // Usar nextLine para capturar toda la línea

                switch (opcion) {
                    case 1 -> buscarLibrosPorTitulo(scanner);
                    case 2 -> buscarAutorPorNombre(scanner);
                    case 3 -> mostrarTodosLosLibros();
                    case 4 -> mostrarTodosLosAutores();
                    case 5 -> listarLibrosPorIdioma(scanner);
                    case 6 -> listarAutoresVivosEnAnio(scanner);
                    case 7 -> eliminarLibroPorId(scanner);
                    case 8 -> {
                        System.out.println("Saliendo del sistema. ¡Hasta luego!");
                        System.exit(0);
                    }
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número.");
            }

        }
    }

    private void buscarLibrosPorTitulo(Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        List<Libro> libros = libroService.buscarPorTitulo(titulo);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con el título: " + titulo);
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void buscarAutorPorNombre(Scanner scanner) {
        System.out.print("Ingrese el nombre o apellido del autor: ");
        String nombre = scanner.nextLine();
        List<AutorDTO> autores = autorService.buscarPorNombre(nombre);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores con el nombre: " + nombre);
        } else {
            autores.forEach(autor -> System.out.println(autor.getId() + ": " + autor.getNombre() + " " + autor.getApellido()));
        }
    }

    private void mostrarTodosLosLibros() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en el sistema.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void mostrarTodosLosAutores() {
        List<AutorDTO> autores = autorService.obtenerTodosLosAutores();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en el sistema.");
        } else {
            autores.forEach(autor -> System.out.println(autor.getId() + ": " + autor.getNombre() + " " + autor.getApellido()));
        }
    }

    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingrese el idioma (por ejemplo, 'EN' para inglés): ");
        String idioma = scanner.nextLine();
        List<Libro> libros = libroService.obtenerLibrosPorIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + idioma);
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosEnAnio(Scanner scanner) {
        System.out.print("Ingrese el año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        LocalDate fechaInicio = LocalDate.of(anio, 1, 1);
        LocalDate fechaFin = LocalDate.of(anio, 12, 31);
        List<AutorDTO> autores = autorService.obtenerAutoresVivosEnAnio(fechaInicio, fechaFin);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año: " + anio);
        } else {
            autores.forEach(autor -> System.out.println(autor.getId() + ": " + autor.getNombre() + " " + autor.getApellido()));
        }
    }

    private void eliminarLibroPorId(Scanner scanner) {
        System.out.print("Ingrese el ID del libro a eliminar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            libroService.eliminarLibroPorId(id);
            System.out.println("Libro eliminado correctamente.");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


