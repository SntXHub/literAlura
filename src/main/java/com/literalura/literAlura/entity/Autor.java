package com.literalura.literAlura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no debe estar vacío")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El apellido no debe estar vacío")
    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_fallecimiento")
    private LocalDate fechaFallecimiento;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    // Constructor vacío para JPA
    public Autor() {}

    // Constructor con parámetros
    public Autor(String nombre, String apellido, LocalDate fechaNacimiento, LocalDate fechaFallecimiento, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
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

