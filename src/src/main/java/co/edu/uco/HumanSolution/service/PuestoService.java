package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.PuestoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.repository.PuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PuestoService {

    private static final Logger logger = LoggerFactory.getLogger(PuestoService.class);

    @Autowired
    private PuestoRepository repository;

    public PuestoEntity crear(PuestoEntity puesto) {
        logger.debug("Creando puesto: {}", puesto.getNombre());

        if (puesto.getNombre() == null || puesto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del puesto es obligatorio");
        }

        if (puesto.getUnidad() == null) {
            throw new IllegalArgumentException("La unidad organizativa es obligatoria");
        }

        if (repository.existsByNombreAndUnidad(puesto.getNombre(), puesto.getUnidad())) {
            throw new IllegalArgumentException("Ya existe un puesto con ese nombre en la misma unidad");
        }

        return repository.save(puesto);
    }

    public PuestoEntity asignarUsuario(UUID puestoId, Usuario usuario) {
        PuestoEntity puesto = repository.findById(puestoId)
                .orElseThrow(() -> new IllegalArgumentException("Puesto no encontrado"));

        if (!"Sin asignar".equals(puesto.getEstado().getNombre()) &&
                !"vacante".equals(puesto.getEstado().getNombre())) {
            throw new IllegalArgumentException("El puesto no está disponible para asignación");
        }

        Optional<PuestoEntity> puestoActual = repository.findByUsuario(usuario);
        if (puestoActual.isPresent()) {
            throw new IllegalArgumentException("El usuario ya tiene un puesto asignado");
        }

        puesto.setUsuario(usuario);
        return repository.save(puesto);
    }

    public List<PuestoEntity> listarTodos() {
        return repository.findAll();
    }

    public Optional<PuestoEntity> buscarPorId(UUID id) {
        return repository.findById(id);
    }
}