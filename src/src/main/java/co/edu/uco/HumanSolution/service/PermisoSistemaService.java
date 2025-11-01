package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.PermisoSistemaEntity;
import co.edu.uco.HumanSolution.repository.PermisoSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PermisoSistemaService {

    private static final Logger logger = LoggerFactory.getLogger(PermisoSistemaService.class);

    @Autowired
    private PermisoSistemaRepository repository;

    public PermisoSistemaEntity crear(PermisoSistemaEntity permiso) {
        logger.debug("Creando permiso de sistema: {}", permiso.getNombre());

        if (permiso.getNombre() == null || permiso.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del permiso es obligatorio");
        }

        if (repository.existsByNombre(permiso.getNombre())) {
            throw new IllegalArgumentException("Ya existe un permiso con ese nombre");
        }

        return repository.save(permiso);
    }

    public List<PermisoSistemaEntity> listarTodos() {
        return repository.findAll();
    }

    public Optional<PermisoSistemaEntity> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public void eliminar(UUID id) {
        repository.deleteById(id);
    }
}