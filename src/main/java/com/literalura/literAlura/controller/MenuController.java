package com.literalura.literAlura.controller;

import com.literalura.literAlura.service.LibroService;
import com.literalura.literAlura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nBienvenido al menú principal de LiterAlura. Puedes buscar libros en Español, Inglés, Francés, Portugués, Chino, Alemán, Ruso y Japonés");
            System.out.println("1. Buscar libros por título");
            System.out.println("2. Buscar autor");
            System.out.println("3. Mostrar datos guardados");
            System.out.println("4. Borrar búsquedas de la base de datos");
            System.out.println("5. Mostrar autores vivos en un determinado año");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después del número

            switch (opcion) {
                case 1 -> buscarLibrosPorTitulo(scanner);
                case 2 -> buscarAutor(scanner);
                case 3 -> mostrarDatosGuardados();
                case 4 -> borrarDatosGuardados(scanner);
                case 5 -> mostrarAutoresVivos(scanner);
                case 6 -> {
                    System.out.println("¡Gracias por usar LiterAlura! Hasta pronto.");
                    continuar = false; // Salir del bucle
                }
                default -> System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }

    private void buscarLibrosPorTitulo(Scanner scanner) {
        System.out.print("Introduce el título del libro: ");
        String titulo = scanner.nextLine();
        libroService.buscarPorTitulo(titulo).forEach(System.out::println);
    }

    private void buscarAutor(Scanner scanner) {
        System.out.print("Introduce el nombre del autor: ");
        String autor = scanner.nextLine();
        // Implementa la lógica en el servicio AutorService
        System.out.println("Función no implementada aún.");
    }

    private void mostrarDatosGuardados() {
        System.out.println("Mostrando todos los datos guardados en la base de datos:");
        libroService.obtenerTodosLosLibros().forEach(System.out::println);
    }

    private void borrarDatosGuardados(Scanner scanner) {
        System.out.println("¿Estás seguro de que deseas borrar todos los datos? (sí/no)");
        String confirmacion = scanner.nextLine();
        if ("sí".equalsIgnoreCase(confirmacion)) {
            libroService.borrarTodosLosLibros();
            System.out.println("Datos borrados exitosamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void mostrarAutoresVivos(Scanner scanner) {
        System.out.print("Introduce el año para verificar autores vivos: ");
        int anio = scanner.nextInt();
        autorService.obtenerAutoresVivosEnAnio(anio).forEach(System.out::println);
    }
}
