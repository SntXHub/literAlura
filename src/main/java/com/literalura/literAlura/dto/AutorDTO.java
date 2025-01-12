package com.literalura.literAlura.dto;

public class AutorDTO {

    private Long id; // Identificador único del autor
    private String nombre; // Nombre del autor
    private String apellido; // Apellido del autor
    private String nacionalidad; // Nacionalidad del autor

    // Constructor vacío (necesario para serialización y frameworks como Spring)
    public AutorDTO() {
    }

    // Constructor completo
    public AutorDTO(Long id, String nombre, String apellido, String nacionalidad) {
        this.id = id;
        this.nombre = (nombre != null && !nombre.isBlank()) ? nombre : "Desconocido";
        this.apellido = (apellido != null && !apellido.isBlank()) ? apellido : "Sin información";
        this.nacionalidad = (nacionalidad != null && !nacionalidad.isBlank()) ? nacionalidad : "Sin información";
    }

    // Getters y setters
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
        this.nombre = (nombre != null && !nombre.isBlank()) ? nombre : "Desconocido";
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = (apellido != null && !apellido.isBlank()) ? apellido : "Sin información";
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = (nacionalidad != null && !nacionalidad.isBlank()) ? nacionalidad : "Sin información";
    }

    // Método toString para depuración o salida en consola
    @Override
    public String toString() {
        return String.format("AutorDTO{id=%d, nombre='%s', apellido='%s', nacionalidad='%s'}",
                id, nombre, apellido, nacionalidad);
    }
}
