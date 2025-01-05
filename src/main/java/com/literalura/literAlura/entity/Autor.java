package com.literalura.literAlura.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Autor {

    @Id
    private Long id;
    private String nombre;
    private int fechaNacimiento;
    private int fechaFallecimiento;

    // Constructor por defecto
    public Autor() {}

    // Constructor con parámetros
    public Autor(Long id, String nombre, int fechaNacimiento, int fechaFallecimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(int fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }
}