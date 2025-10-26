package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.UnidadOrganizativaEntity;
import co.edu.uco.HumanSolution.repository.UnidadOrganizativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UnidadOrganizativaService {

    private static final Logger logger = LoggerFactory.getLogger(UnidadOrganizativaService.class);

    @Autowired
    private UnidadOrganizativaRepository repository;

    public UnidadOrganizativaEntity crear(UnidadOrganizativaEntity unidad) {
        logger.debug("Creando unidad organizativa: {}", unidad.getNombre());

        if (unidad.getNombre() == null || unidad.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la unidad organizativa es obligatorio");
        }

        if (repository.existsByNombre(unidad.getNombre())) {
            throw new IllegalArgumentException("Ya existe una unidad organizativa con ese nombre");
        }

        return repository.save(unidad);
    }

    public List<UnidadOrganizativaEntity> listarTodas() {
        return repository.findAll();
    }

    public Optional<UnidadOrganizativaEntity> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public UnidadOrganizativaEntity actualizar(UUID id, UnidadOrganizativaEntity unidad) {
        UnidadOrganizativaEntity existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unidad organizativa no encontrada"));

        existente.setNombre(unidad.getNombre());
        existente.setUnidadSuperior(unidad.getUnidadSuperior());

        return repository.save(existente);
    }

    public void eliminar(UUID id) {
        repository.deleteById(id);
    }
}