package com.literalura.literAlura.service;

import com.literalura.literAlura.dto.AutorDTO;
import com.literalura.literAlura.entity.Autor;
import com.literalura.literAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor guardarOActualizarAutor(Autor autor) {
        Optional<Autor> autorExistente = autorRepository.findByNombre(autor.getNombre());
        return autorExistente.orElseGet(() -> autorRepository.save(autor));
    }

    public List<AutorDTO> buscarPorNombreOApellido(String nombreOApellido) {
        List<Autor> autores = autorRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(nombreOApellido, nombreOApellido);
        return autores.stream()
                .map(autor -> new AutorDTO(autor.getId(), autor.getNombre(), autor.getApellido(), autor.getNacionalidad()))
                .collect(Collectors.toList());
    }

    public List<AutorDTO> obtenerTodosLosAutores() {
        return autorRepository.findAll().stream()
                .map(autor -> new AutorDTO(autor.getId(), autor.getNombre(), autor.getApellido(), autor.getNacionalidad()))
                .collect(Collectors.toList());
    }

    public List<AutorDTO> obtenerAutoresVivosEnAnio(LocalDate inicio, LocalDate fin) {
        List<Autor> autores = autorRepository.findByFechaNacimientoBeforeAndFechaFallecimientoAfter(inicio, fin);
        return autores.stream()
                .map(autor -> new AutorDTO(autor.getId(), autor.getNombre(), autor.getApellido(), autor.getNacionalidad()))
                .collect(Collectors.toList());
    }
}


