package com.literalura.literAlura.service;

import com.literalura.literAlura.dto.AutorDTO;
import com.literalura.literAlura.entity.Autor;
import com.literalura.literAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<AutorDTO> obtenerTodosLosAutores() {
        return autorRepository.findAll()
                .stream()
                .distinct()
                .map(this::convertirADTO)
                .toList();
    }

    public List<AutorDTO> buscarPorNombreOApellido(String criterio) {
        List<Autor> autores = autorRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(criterio, criterio);
        return autores.stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<AutorDTO> obtenerAutoresVivosEnAnio(LocalDate inicio, LocalDate fin) {
        return autorRepository.findAll()
                .stream()
                .filter(autor -> {
                    boolean nacioAntesDeFin = autor.getFechaNacimiento() != null && !autor.getFechaNacimiento().isAfter(fin);
                    boolean sigueVivo = autor.getFechaFallecimiento() == null || autor.getFechaFallecimiento().isAfter(inicio);
                    return nacioAntesDeFin && sigueVivo;
                })
                .distinct()
                .map(this::convertirADTO)
                .toList();
    }

    private AutorDTO convertirADTO(Autor autor) {
        return new AutorDTO(
                autor.getId(),
                autor.getNombre(),
                autor.getApellido(),
                autor.getNacionalidad()
        );
    }
}


