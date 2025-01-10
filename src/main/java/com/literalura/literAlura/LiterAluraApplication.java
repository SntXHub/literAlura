package com.literalura.literAlura;

import com.literalura.literAlura.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// La anotación @SpringBootApplication habilita el escaneo de componentes
@SpringBootApplication(scanBasePackages = "com.literalura.literAlura") // Escanea todos los paquetes hijos
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private MenuController menuController;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Llamada al método para iniciar el menú principal
		menuController.mostrarMenu();
	}
}
