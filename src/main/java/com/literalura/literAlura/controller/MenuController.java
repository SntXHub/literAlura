package com.literalura.literAlura.controller;

import com.literalura.literAlura.service.AutorService;
import org.springframework.stereotype.Controller;

@Controller
public class MenuController {

    private final AutorService autorService;

    // Inyección de dependencias mediante el constructor
    public MenuController(AutorService autorService) {
        this.autorService = autorService;
    }

    public void mostrarMenu() {
        System.out.println("******************************");
        System.out.println("Bienvenido al menú principal de LiterAlura");
        System.out.println("1. Listar autores");
        System.out.println("2. Buscar autor por ID");
        System.out.println("3. Crear nuevo autor");
        System.out.println("4. Actualizar autor");
        System.out.println("5. Eliminar autor");
        System.out.println("6. Salir");
        System.out.println("******************************");

        // Ejemplo de uso de autorService
        System.out.println("Actualmente hay " + autorService.obtenerTodosLosAutores().size() + " autores.");
    }
}