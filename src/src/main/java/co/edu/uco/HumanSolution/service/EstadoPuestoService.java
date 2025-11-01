package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.EstadoPuestoEntity;
import co.edu.uco.HumanSolution.repository.EstadoPuestoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoPuestoService {

    private static final Logger logger = LoggerFactory.getLogger(EstadoPuestoService.class);

    @Autowired
    private EstadoPuestoRepository repository;

    public EstadoPuestoEntity crear(EstadoPuestoEntity estado) {
        logger.debug("Creando estado de puesto: {}", estado.getNombre());

        if (estado.getNombre() == null || estado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estado de puesto es obligatorio");
        }

        if (repository.existsByNombre(estado.getNombre())) {
            throw new IllegalArgumentException("Ya existe un estado de puesto con ese nombre");
        }

        return repository.save(estado);
    }

    public List<EstadoPuestoEntity> listarTodos() {
        return repository.findAll();
    }

    public Optional<EstadoPuestoEntity> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
}