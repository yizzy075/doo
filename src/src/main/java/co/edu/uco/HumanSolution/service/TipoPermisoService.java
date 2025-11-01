package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.TipoPermisoEntity;
import co.edu.uco.HumanSolution.repository.TipoPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TipoPermisoService {

    private static final Logger logger = LoggerFactory.getLogger(TipoPermisoService.class);

    @Autowired
    private TipoPermisoRepository repository;

    public TipoPermisoEntity crear(TipoPermisoEntity tipoPermiso) {
        logger.debug("Creando tipo de permiso: {}", tipoPermiso.getNombre());

        if (tipoPermiso.getNombre() == null || tipoPermiso.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tipo de permiso es obligatorio");
        }

        if (tipoPermiso.getDescripcion() == null || tipoPermiso.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción del tipo de permiso es obligatoria");
        }

        if (repository.existsByNombre(tipoPermiso.getNombre())) {
            throw new IllegalArgumentException("Ya existe un tipo de permiso con ese nombre");
        }

        return repository.save(tipoPermiso);
    }

    public List<TipoPermisoEntity> listarTodos() {
        return repository.findAll();
    }

    public Optional<TipoPermisoEntity> buscarPorId(UUID id) {
        return repository.findById(id);
    }
}