package com.literalura.literAlura.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Libro {

    @Id
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Column(length = 500) // Aumentar el tamaño permitido
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id") // Relación con la tabla "autor"
    private Autor autor;

    private Integer anioPublicacion;

    private String genero;

    @NotBlank(message = "El idioma no puede estar vacío")
    private String idioma;

    // Constructor vacío
    public Libro() {
    }

    // Constructor completo
    public Libro(Long id, String titulo, Autor autor, Integer anioPublicacion, String genero, String idioma) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = (anioPublicacion != null && anioPublicacion > 0) ? anioPublicacion : -1; // Valor predeterminado
        this.genero = genero;
        this.idioma = idioma;
    }

    // Getters y setters
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
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
                ", Autor=" + autor +
                ", Género='" + genero + '\'' +
                ", Idioma='" + idioma + '\'' +
                ", Año=" + (anioPublicacion == null || anioPublicacion == -1 ? "Sin información" : anioPublicacion) +
                '}';
    }
}




