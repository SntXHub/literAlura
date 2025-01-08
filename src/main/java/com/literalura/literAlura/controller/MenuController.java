package com.literalura.literAlura.controller;

import org.springframework.web.client.RestTemplate; // Aquí se coloca la importación
import com.literalura.literAlura.repository.AutorRepository;
import com.literalura.literAlura.repository.LibroRepository;
import com.literalura.literAlura.service.GutendexService;
import com.literalura.literAlura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
public class MenuController {

    private final GutendexService gutendexService;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    private LibroService libroService;

    private final RestTemplate restTemplate; // Instanciamos RestTemplate

    public MenuController(GutendexService gutendexService, LibroRepository libroRepository, AutorRepository autorRepository, RestTemplate restTemplate) {
        this.gutendexService = gutendexService;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.restTemplate = restTemplate; // Asignación del RestTemplate
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
    }
}