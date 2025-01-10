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

    // Método para obtener todos los autores registrados
    public List<AutorDTO> obtenerTodosLosAutores() {
        return autorRepository.findAll().stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    // Método para obtener autores vivos en un año específico
    public List<AutorDTO> obtenerAutoresVivosEnAnio(LocalDate anio) {
        return autorRepository.findByFechaNacimientoBeforeAndFechaFallecimientoAfter(anio, anio)
                .stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    // Método para convertir una entidad Autor a un DTO
    private AutorDTO convertirEntidadADTO(Autor autor) {
        return new AutorDTO(
                autor.getId(),
                autor.getNombre(),
                autor.getApellido(),
                autor.getNacionalidad()
        );
    }
}