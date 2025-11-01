package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.Rol;
import co.edu.uco.HumanSolution.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RolService {

    private static final Logger logger = LoggerFactory.getLogger(RolService.class);

    @Autowired
    private RolRepository repository;

    public Rol crear(Rol rol) {
        logger.debug("Creando rol: {}", rol.getNombre());

        if (rol.getNombre() == null || rol.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol es obligatorio");
        }

        if (repository.existsByNombre(rol.getNombre())) {
            throw new IllegalArgumentException("Ya existe un rol con ese nombre");
        }

        return repository.save(rol);
    }

    public List<Rol> listarTodos() {
        return repository.findAll();
    }

    public Optional<Rol> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public Rol actualizar(UUID id, Rol rol) {
        Rol existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

        existente.setNombre(rol.getNombre());
        existente.setDescripcion(rol.getDescripcion());
        return repository.save(existente);
    }

    public void eliminar(UUID id) {
        repository.deleteById(id);
    }
}