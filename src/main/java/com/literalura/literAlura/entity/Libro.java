package com.literalura.literAlura.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Libro {

    @Id
    private Long id; // ID proporcionado por la API externa

    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    @NotBlank(message = "El autor no puede estar vacío")
    private String autor;

    @Column(name = "anio_publicacion", nullable = false)
    private Integer anioPublicacion = -1; // Valor predeterminado para evitar nulos

    private String genero;

    @NotBlank(message = "El idioma no puede estar vacío")
    @Column(name = "idioma")
    private String idioma;

    // Constructor vacío
    public Libro() {}

    // Constructor completo
    public Libro(Long id, String titulo, String autor, Integer anioPublicacion, String genero, String idioma) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion != null ? anioPublicacion : -1; // Asignar valor predeterminado
        this.genero = genero;
        this.idioma = idioma;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion != null ? anioPublicacion : -1; // Evitar nulos
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "ID=" + id +
                ", Título='" + titulo + '\'' +
                ", Autor='" + autor + '\'' +
                ", Año=" + (anioPublicacion != null && anioPublicacion != -1 ? anioPublicacion : "Desconocido") +
                ", Género='" + (genero != null ? genero : "Desconocido") + '\'' +
                ", Idioma='" + idioma + '\'' +
                '}';
    }
}





