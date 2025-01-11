package com.literalura.literAlura.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.literalura.literAlura.entity.Libro;

import java.util.List;

public class GutendexResponse {

    @JsonProperty("results")
    private List<Libro> libros;

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}