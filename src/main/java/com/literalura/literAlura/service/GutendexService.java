package com.literalura.literAlura.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;

    public GutendexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obtenerLibrosPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;
        return restTemplate.getForObject(url, String.class);
    }
}
