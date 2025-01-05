package com.literalura.literAlura;

import com.literalura.literAlura.controller.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	private final MenuController menuController;

	public LiterAluraApplication(MenuController menuController) {
		this.menuController = menuController;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		menuController.mostrarMenu();
	}
}
