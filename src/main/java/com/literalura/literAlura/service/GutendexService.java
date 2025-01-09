package com.literalura.literAlura.service;

import com.literalura.literAlura.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;

    @Autowired
    public GutendexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Método para obtener los libros por título
    public List<Libro> obtenerLibrosPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;
        // Deserializa la respuesta JSON en un objeto de tipo GutendexResponse
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
        return response != null ? response.getLibros() : null;
    }
}