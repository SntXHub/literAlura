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

    public List<AutorDTO> buscarPorNombre(String nombre) {
        return autorRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(autor -> new AutorDTO(autor.getId(), autor.getNombre(), autor.getApellido(), autor.getNacionalidad()))
                .collect(Collectors.toList());
    }

    public List<AutorDTO> obtenerTodosLosAutores() {
        return autorRepository.findAll().stream()
                .map(autor -> new AutorDTO(autor.getId(), autor.getNombre(), autor.getApellido(), autor.getNacionalidad()))
                .collect(Collectors.toList());
    }

    public List<AutorDTO> obtenerAutoresVivosEnAnio(LocalDate fechaInicio, LocalDate fechaFin) {
        return autorRepository.findByFechaNacimientoBeforeAndFechaFallecimientoAfter(fechaInicio, fechaFin).stream()
                .map(autor -> new AutorDTO(autor.getId(), autor.getNombre(), autor.getApellido(), autor.getNacionalidad()))
                .collect(Collectors.toList());
    }
}


