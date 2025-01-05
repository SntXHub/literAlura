package com.literalura.literAlura.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Libro {

    @Id
    private Long id;
    private String titulo;
    private String idioma;
    private Long autorId;  // Si se desea asociar un autor a cada libro, puedes usar este campo

    // Constructor por defecto
    public Libro() {}

    // Constructor con parámetros
    public Libro(Long id, String titulo, String idioma, Long autorId) {
        this.id = id;
        this.titulo = titulo;
        this.idioma = idioma;
        this.autorId = autorId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }
}
