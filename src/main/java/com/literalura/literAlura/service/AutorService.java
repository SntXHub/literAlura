package com.literalura.literAlura.service;

import com.literalura.literAlura.dto.AutorDTO;
import com.literalura.literAlura.entity.Autor;
import com.literalura.literAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    /**
     * Convierte una entidad Autor en un DTO AutorDTO.
     *
     * @param autor la entidad Autor
     * @return el DTO AutorDTO
     */
    private AutorDTO convertirADTO(Autor autor) {
        return new AutorDTO(
                autor.getId(),
                autor.getNombre() != null ? autor.getNombre() : "Sin información",
                autor.getApellido() != null ? autor.getApellido() : "Sin información",
                autor.getNacionalidad() != null ? autor.getNacionalidad() : "Sin información"
        );
    }

    /**
     * Busca autores por nombre o apellido.
     *
     * @param criterio el criterio de búsqueda
     * @return una lista de DTOs AutorDTO
     */
    public List<AutorDTO> buscarPorNombreOApellido(String criterio) {
        return autorRepository.findDistinctByNombreOrApellidoContainingIgnoreCase(criterio)
                .stream()
                .map(this::convertirADTO)
                .distinct() // Remover duplicados en la lógica
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los autores sin duplicados.
     *
     * @return una lista de DTOs AutorDTO
     */
    public List<AutorDTO> obtenerTodosLosAutores() {
        return autorRepository.findAllDistinct()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Encuentra autores vivos en un año específico.
     *
     * @param fechaInicio inicio del rango de fechas
     * @param fechaFin    fin del rango de fechas
     * @return una lista de DTOs AutorDTO
     */
    public List<AutorDTO> obtenerAutoresVivosEnAnio(LocalDate fechaInicio, LocalDate fechaFin) {
        return autorRepository.findAutoresVivosEnAnio(fechaInicio, fechaFin)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
}


