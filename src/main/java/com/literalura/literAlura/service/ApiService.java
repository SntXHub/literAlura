package com.literalura.literAlura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public String obtenerDatosDeApi() {
        String url = "https://https://gutendex.com/books"; // URL de la API externa
        String response = restTemplate.getForObject(url, String.class);
        return response; // Devuelve la respuesta de la API
    }
}