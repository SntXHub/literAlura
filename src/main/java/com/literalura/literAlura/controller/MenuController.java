package com.literalura.literAlura.controller;

import com.literalura.literAlura.repository.AutorRepository;
import com.literalura.literAlura.repository.LibroRepository;
import com.literalura.literAlura.service.GutendexService;
import com.literalura.literAlura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
public class MenuController {

    private final GutendexService gutendexService;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public MenuController(GutendexService gutendexService, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.gutendexService = gutendexService;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @Autowired
    private LibroService libroService;

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú Principal:");
            System.out.println("1. Buscar libros por título");
            System.out.println("2. Buscar autores por nombre");
            System.out.println("3. Listar libros registrados");
            System.out.println("4. Listar autores registrados");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Listar autores vivos en un determinado año");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");

            int opcion = -1;
            while (opcion < 1 || opcion > 7) {
                try {
                    opcion = scanner.nextInt(); // Espera un número entero
                    if (opcion < 1 || opcion > 7) {
                        System.out.println("Opción no válida. Intente de nuevo.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor ingrese un número entre 1 y 7.");
                    scanner.next(); // Limpiar el buffer para evitar un bucle infinito
                }
            }

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    buscarAutoresPorNombre();
                    break;
                case 3:
                    listarLibrosRegistrados();
                    break;
                case 4:
                    listarAutoresRegistrados();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    listarAutoresVivosEnAno();
                    break;
                case 7:
                    System.out.println("¡Saliendo...");
                    return; // Sale del menú
            }
        }
    }

    @Service
    class BookService {
        public void buscarPorTitulo(String titulo) {
        }
    }

    private void buscarLibrosPorTitulo() {
        System.out.println("Introduzca el título del libro a buscar:");
        Scanner scanner = new Scanner(System.in);
        String titulo = scanner.nextLine();
        // Llama al servicio para buscar los libros por título
        libroService.buscarPorTitulo(titulo);
    }

    private void buscarAutoresPorNombre() {
        System.out.println("Introduzca el nombre o apellido del autor a buscar:");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.nextLine();
        // Llama al servicio para buscar autores por nombre
    }

    private void listarLibrosRegistrados() {
        // Llama al servicio para listar libros registrados
    }

    private void listarAutoresRegistrados() {
        // Llama al servicio para listar autores registrados
    }

    private void listarLibrosPorIdioma() {
        // Llama al servicio para listar libros por idioma
    }

    private void listarAutoresVivosEnAno() {
        int ano = -1;
        System.out.println("Introduzca el año para verificar los autores vivos:");
        Scanner scanner = new Scanner(System.in);
        while (ano <= 0) {
            try {
                ano = scanner.nextInt(); // Espera un número entero
                if (ano <= 0) {
                    System.out.println("Por favor ingrese un año válido.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número válido para el año.");
                scanner.next(); // Limpiar el buffer
            }
        }
        // Llama al servicio para verificar autores vivos en el año
    }
}