package com.literalura.literAlura.controller;

import java.util.Scanner;
import com.literalura.literAlura.service.LibroService;
import com.literalura.literAlura.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {

    private final LibroService libroService;

    @Autowired
    public MenuController(LibroService libroService) {
        this.libroService = libroService;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("==== Menú Principal ====");
            System.out.println("1. Buscar libros por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un año determinado");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Para consumir el salto de línea después del entero

            switch (opcion) {
                case 1:
                    buscarPorTitulo(scanner);
                    break;
                case 2:
                    mostrarTodosLosLibros();
                    break;
                case 3:
                    mostrarTodosLosAutores();
                    break;
                case 4:
                    listarAutoresVivos(scanner);
                    break;
                case 5:
                    listarLibrosPorIdioma(scanner);
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }

    // Método para buscar libros por título
    private void buscarPorTitulo(Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        // Llamar al servicio para buscar el libro
        var libros = libroService.buscarPorTitulo(titulo);
        libros.forEach(libro -> System.out.println(libro.getTitulo()));
    }

    // Método para mostrar todos los libros
    private void mostrarTodosLosLibros() {
        var libros = libroService.obtenerTodosLosLibros();
        libros.forEach(libro -> System.out.println(libro.getTitulo()));
    }

    // Método para mostrar todos los autores
    private void mostrarTodosLosAutores() {
        var autores = libroService.obtenerTodosLosAutores();
        autores.forEach(autor -> System.out.println(autor.getNombre()));
    }

    // Método para listar autores vivos en un año determinado
    private void listarAutoresVivos(Scanner scanner) {
        System.out.print("Ingrese el año: ");
        int anio = scanner.nextInt();
        var autores = libroService.obtenerAutoresVivosEnAnio(anio);
        autores.forEach(autor -> System.out.println(autor.getNombre()));
    }

    // Método para listar libros por idioma
    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingrese el idioma (por ejemplo, ES, EN, FR): ");
        String idioma = scanner.nextLine();
        var libros = libroService.obtenerLibrosPorIdioma(idioma);
        libros.forEach(libro -> System.out.println(libro.getTitulo()));
    }
}