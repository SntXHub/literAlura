package com.literalura.literAlura.controller;

import com.literalura.literAlura.entity.Autor;
import com.literalura.literAlura.entity.Libro;
import com.literalura.literAlura.service.AutorService;
import com.literalura.literAlura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Controller
public class MenuController {

    private final LibroService libroService;
    private final AutorService autorService;

    @Autowired
    public MenuController(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Buscar libros por título");
            System.out.println("2. Buscar autor por nombre o apellido");
            System.out.println("3. Mostrar base de datos de libros");
            System.out.println("4. Borrar búsquedas de la base de datos");
            System.out.println("5. Mostrar autores vivos en un determinado año");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1 -> buscarLibrosPorTitulo(scanner);
                    case 2 -> buscarAutorPorNombre(scanner);
                    case 3 -> mostrarBaseDeDatos();
                    case 4 -> borrarBusquedasDeBaseDeDatos(scanner);
                    case 5 -> mostrarAutoresVivosEnAnio(scanner);
                    case 6 -> {
                        System.out.println("¡Gracias por usar LiterAlura! Saliendo...");
                        return;
                    }
                    default -> System.out.println("Opción inválida. Por favor, intenta nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
    }

    private void buscarLibrosPorTitulo(Scanner scanner) {
        System.out.print("Introduce el título del libro a buscar: ");
        String titulo = scanner.nextLine();
        List<Libro> libros = libroService.buscarPorTitulo(titulo);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con el título especificado.");
        } else {
            System.out.println("\nLibros encontrados:");
            libros.forEach(System.out::println);
        }
    }

    private void buscarAutorPorNombre(Scanner scanner) {
        System.out.print("Introduce el nombre o apellido del autor: ");
        String nombre = scanner.nextLine();
        List<Autor> autores = autorService.buscarPorNombre(nombre);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores con el nombre especificado.");
        } else {
            System.out.println("\nAutores encontrados:");
            autores.forEach(System.out::println);
        }
    }

    private void mostrarBaseDeDatos() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            System.out.println("\nLibros registrados:");
            libros.forEach(System.out::println);
        }
    }

    private void borrarBusquedasDeBaseDeDatos(Scanner scanner) {
        System.out.print("Introduce el ID del libro a eliminar: ");
        try {
            Long id = scanner.nextLong();
            boolean eliminado = libroService.eliminarLibroPorId(id);

            if (eliminado) {
                System.out.println("Libro eliminado con éxito.");
            } else {
                System.out.println("No se encontró un libro con el ID especificado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido. Por favor, introduce un número válido.");
            scanner.nextLine(); // Limpiar buffer
        }
    }

    private void mostrarAutoresVivosEnAnio(Scanner scanner) {
        System.out.print("Introduce el año: ");
        try {
            int anio = scanner.nextInt();
            List<Autor> autores = autorService.obtenerAutoresVivosEnAnio(anio);

            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año especificado.");
            } else {
                System.out.println("\nAutores vivos en el año " + anio + ":");
                autores.forEach(System.out::println);
            }
        } catch (InputMismatchException e) {
            System.out.println("Año inválido. Por favor, introduce un número válido.");
            scanner.nextLine(); // Limpiar buffer
        }
    }
}
