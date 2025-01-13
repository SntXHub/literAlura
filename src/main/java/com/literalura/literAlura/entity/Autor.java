package com.literalura.literAlura.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    private LocalDate fechaNacimiento;
    private LocalDate fechaFallecimiento;
    private String nacionalidad;

    // Constructor vacío
    public Autor() {}

    // Constructor con parámetros
    public Autor(String nombre, String apellido, LocalDate fechaNacimiento, LocalDate fechaFallecimiento, String nacionalidad) {
        this.nombre = nombre != null ? nombre : "Desconocido";
        this.apellido = apellido != null ? apellido : "Desconocido";
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.nacionalidad = nacionalidad != null ? nacionalidad : "Sin información";
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    // Método para obtener el nombre completo
    public String getNombreCompleto() {
        return (nombre != null ? nombre : "Desconocido") +
                (apellido != null ? " " + apellido : "");
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + (fechaNacimiento != null ? fechaNacimiento : "Sin información") +
                ", fechaFallecimiento=" + (fechaFallecimiento != null ? fechaFallecimiento : "Sin información") +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}

