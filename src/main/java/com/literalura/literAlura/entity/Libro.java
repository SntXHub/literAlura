package com.literalura.literAlura.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Libro {

    @Id
    private Long id; // El ID es proporcionado por la API externa

    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    @NotBlank(message = "El autor no puede estar vacío")
    private String autor;

    private Integer anio_Publicacion; // Año como Integer para soportar valores nulos

    private String genero;

    @NotBlank(message = "El idioma no puede estar vacío")
    private String idioma;

    // Constructor vacío
    public Libro() {}

    // Constructor completo
    public Libro(Long id, String titulo, String autor, Integer anio_Publicacion, String genero, String idioma) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio_Publicacion = anio_Publicacion;
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

    public Integer getAnio_Publicacion() {
        return anio_Publicacion;
    }

    public void setAnio_Publicacion(Integer anio_Publicacion) {
        this.anio_Publicacion = anio_Publicacion;
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
                ", Año=" + (anio_Publicacion != null ? anio_Publicacion : "Desconocido") +
                ", Género='" + (genero != null ? genero : "Desconocido") + '\'' +
                ", Idioma='" + idioma + '\'' +
                '}';
    }
}




