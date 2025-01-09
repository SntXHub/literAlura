package com.literalura.literAlura.service;

import com.literalura.literAlura.dto.AutorDTO;
import com.literalura.literAlura.entity.Autor;
import com.literalura.literAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    // Constructor con inyección de dependencia a través del constructor
    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Convertir Autor a AutorDTO
    private AutorDTO convertirEntidadADTO(Autor autor) {
        return new AutorDTO(
                autor.getId(),
                autor.getNombre(),
                autor.getApellido(),
                autor.getNacionalidad()
        );
    }

    // Convertir AutorDTO a Autor
    private Autor convertirDTOAEntidad(AutorDTO autorDTO) {
        Autor autor = new Autor();
        autor.setId(autorDTO.getId());
        autor.setNombre(autorDTO.getNombre());
        autor.setApellido(autorDTO.getApellido());
        autor.setNacionalidad(autorDTO.getNacionalidad());
        return autor;
    }

    // Obtener todos los autores
    public List<AutorDTO> obtenerTodosLosAutores() {
        return autorRepository.findAll()
                .stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    // Obtener un autor por su ID
    public AutorDTO obtenerAutorPorId(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));
        return convertirEntidadADTO(autor);
    }

    // Crear un nuevo autor
    public AutorDTO crearAutor(AutorDTO autorDTO) {
        Autor autor = convertirDTOAEntidad(autorDTO);
        Autor autorGuardado = autorRepository.save(autor);
        return convertirEntidadADTO(autorGuardado);
    }

    // Actualizar un autor existente
    public AutorDTO actualizarAutor(Long id, AutorDTO autorDTO) {
        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));

        autorExistente.setNombre(autorDTO.getNombre());
        autorExistente.setApellido(autorDTO.getApellido());
        autorExistente.setNacionalidad(autorDTO.getNacionalidad());

        Autor autorActualizado = autorRepository.save(autorExistente);
        return convertirEntidadADTO(autorActualizado);
    }

    // Eliminar un autor
    public void eliminarAutor(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));
        autorRepository.delete(autor);
    }

    public Iterable<Object> obtenerAutoresVivosEnAnio(int anio) {
    }
}
