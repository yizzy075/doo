package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.EstadoSolicitudEntity;
import co.edu.uco.HumanSolution.repository.EstadoSolicitudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoSolicitudService {

    private static final Logger logger = LoggerFactory.getLogger(EstadoSolicitudService.class);

    @Autowired
    private EstadoSolicitudRepository repository;

    public EstadoSolicitudEntity crear(EstadoSolicitudEntity estado) {
        logger.debug("Creando estado de solicitud: {}", estado.getNombre());

        if (estado.getNombre() == null || estado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estado es obligatorio");
        }

        if (repository.existsByNombre(estado.getNombre())) {
            throw new IllegalArgumentException("Ya existe un estado con ese nombre");
        }

        return repository.save(estado);
    }

    public List<EstadoSolicitudEntity> listarTodos() {
        return repository.findAll();
    }

    public Optional<EstadoSolicitudEntity> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
}